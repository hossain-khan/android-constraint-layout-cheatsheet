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
import android.support.annotation.LayoutRes
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.base.AppConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LayoutDataStore @Inject constructor(
        private val resources: Resources) {
    val supportedLayoutInfos = listOf(
            LayoutInformation(
                    layoutResourceId = R.layout.preview_positioning_top_left,
                    thumbnailResourceId = R.drawable.thumb_positioning_top_left,
                    title = "Positioning: Top Left",
                    description = "Top left using constraints."),
            LayoutInformation(
                    layoutResourceId = R.layout.preview_positioning_centered,
                    thumbnailResourceId = R.drawable.thumb_positioning_center,
                    title = "Positioning: Centered",
                    description = "Centered view using constraints on top-bottom and left-right."),
            LayoutInformation(
                    layoutResourceId = R.layout.preview_positioning_circular,
                    thumbnailResourceId = R.drawable.thumb_positioning_circular,
                    title = "Positioning: Circular",
                    description = "You can constrain a widget center relative to another widget center, at an angle and a distance. This allows you to position a widget on a circle." +
                            "\n\n" +
                            "layout_constraintCircle : references another widget id\n" +
                            "layout_constraintCircleRadius : the distance to the other widget center\n" +
                            "layout_constraintCircleAngle : which angle the widget should be at (in degrees, from 0 to 360)\n")
    )

    /**
     * A map of layout information by layout resource ID.
     */
    val layoutsInfos: Map<Int, LayoutInformation> by lazy {
        supportedLayoutInfos.associateBy { it.layoutResourceId }
    }

    /**
     * Returns Github URL for layout resource file for this project.
     *
     * @param layoutResourceId The layout resource ID to generate the URL for.
     * @return The URL to load the layout blob file.
     */
    fun getLayoutUrl(@LayoutRes layoutResourceId: Int): String {
        // Containes package name and layout name
        // com.hossainkhan.android.demo:layout/preview_positioning_top_left
        val resourceName = resources.getResourceName(layoutResourceId)

        if(!resourceName.contains("layout")) {
            throw IllegalStateException("Only layout resource is allowed.")
        }

        return AppConfig.GITHUB_BASE_URL.plus("/blob/master/app/src/main/res/") +
                resourceName.split(':')
                .last().plus(".xml")
    }
}