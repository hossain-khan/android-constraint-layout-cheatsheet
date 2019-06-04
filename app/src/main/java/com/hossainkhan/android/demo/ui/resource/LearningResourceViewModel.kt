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

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.hossainkhan.android.demo.data.ResourceInfo
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class LearningResourceViewModel @Inject constructor(
        firestore: FirebaseFirestore
) : ViewModel() {
    companion object {
        private const val RESOURCE_COLLECTION = "external-resources"
    }

    val isLoading = ObservableField(true)

    private val _data = MutableLiveData<List<ResourceInfo>>()
    val data: LiveData<List<ResourceInfo>> = _data

    private val resourceData = mutableListOf<ResourceInfo>()

    init {
        Timber.i("Loading data from firestore: %s", firestore)
        firestore.collection(RESOURCE_COLLECTION)
                .orderBy(ResourceInfo::publish_date.name, Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(this::updateResources)
                .addOnFailureListener(this::onLoadFailed)
    }

    private fun updateResources(result: QuerySnapshot) {
        for (document in result) {
            val x = document.toObject(ResourceInfo::class.java)
            Timber.i("Resource: $x")
            resourceData.add(x)
        }
        isLoading.set(false)
        _data.value = resourceData
    }

    private fun onLoadFailed(exception: Exception) {
        Timber.w(exception, "Error getting documents.")
        isLoading.set(false)
    }
}