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

package com.hossainkhan.android.demo.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests [ResourceInfo].
 */
class ResourceInfoTest {

    @Test
    fun isYouTubeUrl_givenValidUrl_returnsTrue() {
        val resourceInfoYoutube = ResourceInfo(url = "https://www.youtube.com/watch?v=29gLA90m6Gk")

        assertTrue(resourceInfoYoutube.isYouTubeUrl)
    }

    @Test
    fun isYouTubeUrl_givenInvalidUrl_returnsFalse() {
        val resourceInfoYoutube = ResourceInfo(url = "https://hossainkhan.com/watch?v=29gLA90m6Gk")

        assertFalse(resourceInfoYoutube.isYouTubeUrl)
    }

    @Test
    fun getYoutubeIntentUri_givenValidUrl_providesYoutubeIntentUri() {
        val resourceInfoYoutube = ResourceInfo(url = "https://www.youtube.com/watch?v=29gLA90m6Gk")

        assertEquals("vnd.youtube:29gLA90m6Gk", resourceInfoYoutube.youtubeIntentUri)
    }

    @Test(expected = IllegalStateException::class)
    fun getYoutubeIntentUri_givenInvalidUrl_throwsException() {
        val resourceInfoYoutube = ResourceInfo(url = "https://hossainkhan.com")

        resourceInfoYoutube.youtubeIntentUri
    }
}