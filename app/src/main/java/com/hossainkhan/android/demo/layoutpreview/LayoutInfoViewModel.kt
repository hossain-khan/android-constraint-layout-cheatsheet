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

package com.hossainkhan.android.demo.layoutpreview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.LayoutRes
import com.hossainkhan.android.demo.data.AppDataStore
import com.hossainkhan.android.demo.data.LayoutInformation
import timber.log.Timber

/**
 * ViewModel for containing layout information.
 */
class LayoutInfoViewModel(private val appDataStore: AppDataStore) : ViewModel() {
    private val layoutInfoLiveData: MutableLiveData<LayoutInformation> = MutableLiveData()

    val layoutInformation: LiveData<LayoutInformation>
        get() = layoutInfoLiveData

    /**
     * Provides layout URL for the currently initialized layout resource id.
     * @see init
     */
    val layoutUrl: String
        get() = appDataStore.layoutStore.getLayoutUrl(layoutInfoLiveData.value!!.layoutResourceId)

    /**
     * Indicates if the current screen is being shown for the first time.
     */
    val isFirstTime: Boolean
        get() = appDataStore.shouldshowLayoutInformation(layoutInfoLiveData.value!!.layoutResourceId)


    fun init(@LayoutRes layoutResourceId: Int) {
        val layoutInfo = appDataStore.layoutStore.layoutsInfos[layoutResourceId]!!
        Timber.d("Loading layout: %s", layoutInfoLiveData)

        layoutInfoLiveData.value = layoutInfo
    }
}