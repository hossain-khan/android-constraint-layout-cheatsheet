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

import com.hossainkhan.android.demo.R

class LayoutDataStore() {
    private val supportedLayoutInfos = listOf(
            LayoutInformation(
                    layoutResourceId = R.layout.activity_positioning_top_left,
                    title = "Positioning: Top Left",
                    description = "Top left using constraints."),
            LayoutInformation(
                    layoutResourceId = R.layout.activity_positioning_centered,
                    title = "Positioning: Centered",
                    description = "Centered view using constraints on top-bottom and left-right.")
    )

    /**
     * A map of layout information by layout resource ID.
     */
    val layoutsInfos: Map<Int, LayoutInformation> =
            supportedLayoutInfos.associateBy { it.layoutResourceId }
}