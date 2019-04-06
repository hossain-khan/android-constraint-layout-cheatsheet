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

package com.hossainkhan.android.demo.browse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.layoutpreview.LayoutChainStyleActivity
import com.hossainkhan.android.demo.layoutpreview.LayoutGuidelineBarrierActivity
import com.hossainkhan.android.demo.layoutpreview.LayoutGuidelineGroupActivity
import com.hossainkhan.android.demo.layoutpreview.LayoutPreviewBaseActivity
import com.hossainkhan.android.demo.layoutpreview.LayoutVisibilityGoneActivity
import com.hossainkhan.android.demo.viewmodel.LayoutPreviewViewModelFactory
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

/**
 * A list activity that shows all the available example demo layouts.
 */
class LayoutBrowseActivity : AppCompatActivity() {
    @Inject
    internal lateinit var viewModelFactory: LayoutPreviewViewModelFactory

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var viewModel: LayoutBrowseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LayoutBrowseViewModel::class.java)

        setupLayoutInfoAdapter(viewModel)
    }

    private fun setupLayoutInfoAdapter(viewModel: LayoutBrowseViewModel) {
        viewManager = GridLayoutManager(this, resources.getInteger(R.integer.grid_column_count))
        viewAdapter = LayoutBrowseAdapter(
                viewModel = viewModel,
                lifecycleOwner = this,
                itemSelectedListener = this::onLayoutItemSelected)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }

    private fun onLayoutItemSelected(layoutResId: Int) {
        Timber.i("Selected layout id: %s", layoutResId)

        /*
         * Where applicable, loads specific activity with interactive feature for user to try out feature.
         */
        when (layoutResId) {
            R.layout.preview_visibility_gone -> {
                startActivity(LayoutPreviewBaseActivity.createStartIntent(this,
                        LayoutVisibilityGoneActivity::class.java, R.layout.preview_visibility_gone))
            }
            R.layout.preview_chain_style_main -> {
                startActivity(LayoutPreviewBaseActivity.createStartIntent(this,
                        LayoutChainStyleActivity::class.java, R.layout.preview_chain_style_main))
            }
            R.layout.preview_virtual_helper_barrier -> {
                startActivity(LayoutPreviewBaseActivity.createStartIntent(this,
                        LayoutGuidelineBarrierActivity::class.java, R.layout.preview_virtual_helper_barrier))
            }
            R.layout.preview_virtual_helper_group -> {
                startActivity(LayoutPreviewBaseActivity.createStartIntent(this,
                        LayoutGuidelineGroupActivity::class.java, R.layout.preview_virtual_helper_group))
            }
            else -> {
                // By default it loads the preview activity with the layout requested.
                startActivity(LayoutPreviewBaseActivity.createStartIntent(this, layoutResId))
            }
        }

    }
}
