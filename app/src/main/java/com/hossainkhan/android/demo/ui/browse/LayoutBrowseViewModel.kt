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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.data.AppDataStore
import com.hossainkhan.android.demo.data.LayoutInformation
import com.hossainkhan.android.demo.ui.common.LiveEvent
import com.hossainkhan.android.demo.ui.functionaldemo.MovieDetailsPreviewActivity
import com.hossainkhan.android.demo.ui.functionaldemo.TedTalkPlaybackActivity
import com.hossainkhan.android.demo.ui.layoutpreview.LayoutChainStyleActivity
import com.hossainkhan.android.demo.ui.layoutpreview.LayoutDimensionMinMaxActivity
import com.hossainkhan.android.demo.ui.layoutpreview.LayoutGuidelineBarrierActivity
import com.hossainkhan.android.demo.ui.layoutpreview.LayoutGuidelineGroupActivity
import com.hossainkhan.android.demo.ui.layoutpreview.LayoutVisibilityGoneActivity
import com.hossainkhan.android.demo.ui.resource.LearningResourceActivity
import timber.log.Timber
import javax.inject.Inject

class LayoutBrowseViewModel @Inject constructor(
        appDataStore: AppDataStore
) : ViewModel() {

    private val _browseResult = LiveEvent<BrowseResult<Any>>()
    val browseResult: LiveData<BrowseResult<Any>> = _browseResult
    private val layoutInfoListLiveData = MutableLiveData<List<LayoutInformation>>()

    val layoutInfos: LiveData<List<LayoutInformation>>
        get() = layoutInfoListLiveData

    init {
        Timber.d("Is first time user: ${appDataStore.isFirstTime()}")
        appDataStore.updateFirstTimeUser(false)


        layoutInfoListLiveData.value = appDataStore.layoutStore.supportedLayoutInfos
    }


    fun onLayoutItemSelected(layoutResId: Int) {
        Timber.i("Selected layout id: %s", layoutResId)

        /*
         * Where applicable, loads specific activity with interactive feature for user to try out feature.
         */
        when (layoutResId) {
            R.layout.preview_visibility_gone -> {
                _browseResult.value = BrowseResult(LayoutVisibilityGoneActivity::class.java, layoutResId)
            }
            R.layout.preview_chain_style_main -> {
                _browseResult.value = BrowseResult(LayoutChainStyleActivity::class.java, layoutResId)
            }
            R.layout.preview_virtual_helper_barrier -> {
                _browseResult.value = BrowseResult(LayoutGuidelineBarrierActivity::class.java, layoutResId)
            }
            R.layout.preview_virtual_helper_group -> {
                _browseResult.value = BrowseResult(LayoutGuidelineGroupActivity::class.java, layoutResId)
            }
            R.layout.preview_dimension_min_max -> {
                _browseResult.value = BrowseResult(LayoutDimensionMinMaxActivity::class.java, layoutResId)
            }
            R.layout.demo_movie_details -> {
                _browseResult.value = BrowseResult(MovieDetailsPreviewActivity::class.java, layoutResId)
            }
            R.layout.demo_ted_talk_playback -> {
                _browseResult.value = BrowseResult(TedTalkPlaybackActivity::class.java, layoutResId)
            }
            R.layout.activity_learning_resource -> {
                _browseResult.value = BrowseResult(LearningResourceActivity::class.java)
            }
            else -> {
                // By default it loads the preview activity with the layout requested.
                _browseResult.value = BrowseResult(layoutResId = layoutResId)
            }
        }

    }

    fun onExternalResourceSelected() {
        _browseResult.value = BrowseResult(LearningResourceActivity::class.java)
    }
}