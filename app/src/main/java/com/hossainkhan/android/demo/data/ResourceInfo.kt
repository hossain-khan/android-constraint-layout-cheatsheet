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

import android.net.Uri
import com.google.firebase.Timestamp
import java.util.Date


/**
 * External resource info.
 *
 * Example:
 * - author: Nicolas Roard & John Hoford
 * - event: Google I/O'19
 * - publish_date: 9 May 2019
 * - summary: Learn the capabilities of ConstraintLayout
 * - title: What's New in ConstraintLayout
 * - type: techtalk
 * - url: https://www.youtube.com/watch?v=29gLA90m6Gk
 */
data class ResourceInfo(
        var author: String = "",
        var summary: String = "",
        var title: String = "",
        var event: String = "",
        var url: String = "",
        var publish_date: Timestamp? = null,
        /**
         * Possible values:
         * "techarticle", "techtalk"
         */
        var type: String = "techtalk"
) {
    val formattedDate: String
        get() {
            val date = publish_date?.toDate() ?: Date()
            return FirestoreDateFormatter.date(date)
        }

    val isYouTubeUrl: Boolean = url.contains("youtube.com/watch?v=", ignoreCase = true)

    /**
     * Provides [Uri] for directly invoking youtube app via Android Intent.
     * @throws IllegalStateException if the URL is not for youtube. Check [isYouTubeUrl] first.
     */
    val youtubeIntentUri: String
        get() = if (isYouTubeUrl) ("vnd.youtube:" + url.split("=")[1]) else throw IllegalStateException("Not a YouTube URL.")
}