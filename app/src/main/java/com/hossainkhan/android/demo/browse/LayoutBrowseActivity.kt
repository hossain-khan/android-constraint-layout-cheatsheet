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

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.data.AppDataStore
import com.hossainkhan.android.demo.layoutpreview.LayoutPreviewBaseActivity
import com.hossainkhan.android.demo.layoutpreview.LayoutVisibilityGoneActivity
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class LayoutBrowseActivity : AppCompatActivity() {
    @Inject
    lateinit var appDataStore: AppDataStore

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.d("Got data: ${appDataStore.isFirstTime()}")
        appDataStore.updateFirstTimeUser(false)

        viewManager = GridLayoutManager(this, resources.getInteger(R.integer.grid_column_count))
        viewAdapter = LayoutBrowseAdapter(
                data = appDataStore.layoutStore.supportedLayoutInfos,
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

        when (layoutResId) {
            R.layout.preview_visibility_gone -> {
                startActivity(LayoutVisibilityGoneActivity.createStartIntent(this))
            }
            else -> {
                startActivity(LayoutPreviewBaseActivity.createStartIntent(this, layoutResId))
            }
        }

    }
}
