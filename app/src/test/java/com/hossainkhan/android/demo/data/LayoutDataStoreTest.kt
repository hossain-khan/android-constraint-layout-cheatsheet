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

package com.hossainkhan.android.demo.data

import android.content.res.Resources
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.`when`

class LayoutDataStoreTest {

    private lateinit var sut: LayoutDataStore
    private val resources: Resources = Mockito.mock(Resources::class.java)

    @Before
    fun setUp() {
        sut = LayoutDataStore(resources)
    }

    @Test
    fun getLayoutsInfos_hasLayoutData() {
        assertFalse("Should contain at least 1 item",
                sut.layoutsInfos.isEmpty())
    }

    /**
     * Tests conversion from layout file to URL to preview on the web.
     *
     * Input:
     * com.hossainkhan.android.demo:layout/preview_positioning_top_left
     *
     * Output:
     * https://github.com/amardeshbd/android-constraint-layout-cheatsheet/blob/master/app/src/main/res/layout/preview_positioning_top_leftxml
     */
    @Test
    fun getLayoutUrl_givenValidLayout_providesUrlToLayoutResource() {
        `when`(resources.getResourceName(anyInt()))
                .thenReturn("com.hossainkhan.android.demo:layout/preview_positioning_top_left")


        assertEquals("https://github.com/amardeshbd/android-constraint-layout-cheatsheet/blob/master/app/src/main/res/layout/preview_positioning_top_left.xml",
                sut.getLayoutUrl(1))
    }

    @Test(expected = IllegalStateException::class)
    fun getLayoutUrl_givenInvalidLayout_providesNull() {
        `when`(resources.getResourceName(anyInt()))
                .thenReturn("some-random-resource")

        sut.getLayoutUrl(1)
    }
}