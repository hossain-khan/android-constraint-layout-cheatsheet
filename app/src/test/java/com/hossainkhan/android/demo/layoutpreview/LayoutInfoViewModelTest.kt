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

import android.content.SharedPreferences
import android.content.res.Resources
import com.hossainkhan.android.demo.data.AppDataStore
import com.hossainkhan.android.demo.data.LayoutDataStore
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.mock
import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.TestRule
import org.junit.Rule


class LayoutInfoViewModelTest {
    /**
     * Uses rule to test LiveData
     *
     * References:
     *  - https://medium.com/pxhouse/unit-testing-with-mutablelivedata-22b3283a7819
     *  - https://stackoverflow.com/questions/29945087/kotlin-and-new-activitytestrule-the-rule-must-be-public
     */
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val resources: Resources = mock(Resources::class.java)
    private val preferences: SharedPreferences = mock(SharedPreferences::class.java)
    private val layoutStore = LayoutDataStore(resources)

    private val mockLayoutId = layoutStore.supportedLayoutInfos.first().layoutResourceId

    lateinit var sut: LayoutInfoViewModel

    @Before
    fun setup() {
        sut = LayoutInfoViewModel(AppDataStore(preferences, layoutStore))
    }

    @Test
    fun getLayoutInformation_givenLayoutId_providesLayoutInformationViaLiveData() {
        sut.init(mockLayoutId)

        assertEquals(layoutStore.layoutsInfos[mockLayoutId], sut.layoutInformation.value)
    }

}