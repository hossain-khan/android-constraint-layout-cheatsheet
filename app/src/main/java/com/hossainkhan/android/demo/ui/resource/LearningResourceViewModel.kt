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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hossainkhan.android.demo.data.ResourceInfo
import javax.inject.Inject

class LearningResourceViewModel @Inject constructor() : ViewModel() {
    private val _data = MutableLiveData<List<ResourceInfo>>()
    val data: LiveData<List<ResourceInfo>> = _data

    private val sampleData = mutableListOf<ResourceInfo>()

    init {
        generateSampleDataSet()
        _data.value = sampleData.toList()
    }

    private fun generateSampleDataSet() {
        for (i in 1..20) {
            //sampleData.add(ResourceInfo(i))
        }
    }



    fun onItemClicked(item: ResourceInfo) {

    }
}