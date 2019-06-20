/*
 * Copyright (c) 2019 Hossain Khan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hossainkhan.android.demo.ui.resource

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.data.ResourceInfo
import com.hossainkhan.android.demo.databinding.ActivityLearningResourceBinding
import com.hossainkhan.android.demo.viewmodel.ViewModelProviderFactory
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

/**
 * This activity lists external resources on [ConstraintLayout], such as tech talks on youtube.
 */
class LearningResourceActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProviderFactory
    private lateinit var viewModel: LearningResourceViewModel
    private lateinit var binding: ActivityLearningResourceBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_learning_resource)

        viewModel = ViewModelProvider(this, viewModelFactory).get(LearningResourceViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupData(binding.recyclerView)
    }


    private fun setupData(ideaList: RecyclerView) {

        val ideaListAdapter = ResourceListAdapter { data ->
            Timber.d("Item Clicked: $data")
            openContent(data)
        }

        ideaList.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@LearningResourceActivity)
            adapter = ideaListAdapter
        }

        viewModel.data.observe(this, Observer { result ->
            if (result?.error != null) {
                Timber.w(result.error, "Unable to load resources.")
                Snackbar.make(binding.root, R.string.message_resource_load_failed, Snackbar.LENGTH_INDEFINITE).show()
                return@Observer
            } else {
                ideaListAdapter.submitList(result.resources)
            }
        })
    }

    private fun openContent(resourceInfo: ResourceInfo) {
        val urlPreviewUri = Uri.parse(resourceInfo.url) // Generic URL preview using any supported app.

        val previewContentIntent = if (resourceInfo.isYouTubeUrl) {
            Intent(Intent.ACTION_VIEW, Uri.parse(resourceInfo.youtubeIntentUri))
        } else {
            Intent(Intent.ACTION_VIEW, urlPreviewUri)
        }

        try {
            startActivity(previewContentIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, urlPreviewUri))
        }
    }
}
