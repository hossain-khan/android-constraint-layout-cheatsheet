/*
 * Copyright (c) 2018 Hossain Khan
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

package com.hossainkhan.android.demo.ui.browse

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.databinding.ActivityMainBinding
import com.hossainkhan.android.demo.ui.layoutpreview.LayoutPreviewBaseActivity
import com.hossainkhan.android.demo.viewmodel.ViewModelProviderFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * A list activity that shows all the available example demo layouts.
 */
class LayoutBrowseActivity : AppCompatActivity() {
    @Inject
    internal lateinit var viewModelFactory: ViewModelProviderFactory

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var viewModel: LayoutBrowseViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LayoutBrowseViewModel::class.java)

        setupLayoutInfoAdapter(viewModel, binding.recyclerView)
        observeBrowseEvent()
    }

    private fun setupLayoutInfoAdapter(viewModel: LayoutBrowseViewModel, recyclerView: RecyclerView) {
        viewManager = GridLayoutManager(this, resources.getInteger(R.integer.grid_column_count))
        viewAdapter = LayoutBrowseAdapter(
                viewModel = viewModel,
                lifecycleOwner = this,
                itemSelectedListener = viewModel::onLayoutItemSelected)

        recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }

    private fun observeBrowseEvent() {
        viewModel.browseResult.observe(this, Observer { result ->
            if (result.layoutResId == null) {
                // Load separate activity not related to layout preview
                startActivity(Intent(this@LayoutBrowseActivity, result.clazz))
            } else {
                if (result.clazz != null) {
                    val startIntent = LayoutPreviewBaseActivity
                            .createStartIntent(this@LayoutBrowseActivity, result.clazz, result.layoutResId)
                    startActivity(startIntent)
                } else {
                    // Load regular layout preview activity
                    val startIntent = LayoutPreviewBaseActivity
                            .createStartIntent(this@LayoutBrowseActivity, result.layoutResId)
                    startActivity(startIntent)
                }
            }
        })
    }

    //
    // Setup menu item on the action bar.
    //
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_layout_browse, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.show_external_resource -> {
                viewModel.onExternalResourceSelected()
                true
            }
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button
                // https://developer.android.com/training/implementing-navigation/ancestral
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
