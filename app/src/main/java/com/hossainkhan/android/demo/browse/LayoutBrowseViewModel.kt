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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hossainkhan.android.demo.data.AppDataStore
import com.hossainkhan.android.demo.data.LayoutInformation
import timber.log.Timber

class LayoutBrowseViewModel(
        appDataStore: AppDataStore) : ViewModel() {

    private val layoutInfoListLiveData = MutableLiveData<List<LayoutInformation>>()

    val layoutInfos: LiveData<List<LayoutInformation>>
        get() = layoutInfoListLiveData

    init {
        Timber.d("Got data: ${appDataStore.isFirstTime()}")
        appDataStore.updateFirstTimeUser(false)


        layoutInfoListLiveData.value = appDataStore.layoutStore.supportedLayoutInfos
    }
}