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
import androidx.annotation.LayoutRes
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.base.AppConfig
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Provides layout data, which is currently static based on what is available.
 *
 * In future, we may store the title and description based on layout ID.
 */
@Singleton
class LayoutDataStore @Inject constructor(
        private val resources: Resources) {
    /**
     * Various layouts features and it's information mostly taken from official documentation.
     *
     * See:
     * - https://github.com/amardeshbd/android-constraint-layout-cheatsheet/blob/master/app/src/main/res/layout/.README.md
     * - https://developer.android.com/reference/android/support/constraint/ConstraintLayout#VisibilityBehavior
     */
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
                    layoutResourceId = R.layout.preview_positioning_bias,
                    thumbnailResourceId = R.drawable.thumb_positioning_bias,
                    title = "Positioning: Horizontal and Vertical Bias",
                    description = "The default when encountering such opposite constraints is to center the widget; but you can tweak the positioning to favor one side over another using the bias attributes:" +
                            "\n\n" +
                            " * layout_constraintHorizontal_bias\n" +
                            " * layout_constraintVertical_bias"),
            LayoutInformation(
                    layoutResourceId = R.layout.preview_positioning_circular,
                    thumbnailResourceId = R.drawable.thumb_positioning_circular,
                    title = "Positioning: Circular",
                    description = "You can constrain a widget center relative to another widget center, at an angle and a distance. This allows you to position a widget on a circle." +
                            "\n\n" +
                            "layout_constraintCircle : references another widget id\n" +
                            "layout_constraintCircleRadius : the distance to the other widget center\n" +
                            "layout_constraintCircleAngle : which angle the widget should be at (in degrees, from 0 to 360)\n"),
            LayoutInformation(
                    layoutResourceId = R.layout.preview_visibility_gone,
                    thumbnailResourceId = R.drawable.thumb_visibility_behaviour,
                    title = "Visibility: GONE behaviour",
                    description = "A view marked as GONE are not going to be displayed and are not part of the layout itself.\n" +
                            "But in terms of the layout computations, GONE widgets are still part of it, with an important distinction:" +
                            "\n\n" +
                            " * For the layout pass, their dimension will be considered as zero (basically, they will be resolved to a point)\n" +
                            " * If they have constraints to other widgets they will still be respected, but any margins will be as if equals to zero"),
            LayoutInformation(
                    layoutResourceId = R.layout.preview_chain_style_main,
                    thumbnailResourceId = R.drawable.thumb_chain_style,
                    title = "Chain: Pack Style",
                    description = "When setting the attribute `constraintHorizontal_chainStyle` or " +
                            "`constraintVertical_chainStyle` on the first element of a chain, " +
                            "the behavior of the chain will change according to the specified style (default is CHAIN_SPREAD)." +
                            "\n\n" +
                            " * CHAIN_SPREAD -- the elements will be spread out (default style)\n" +
                            " * Weighted chain -- in CHAIN_SPREAD mode, if some widgets are set to MATCH_CONSTRAINT, they will split the available space\n" +
                            " * CHAIN_SPREAD_INSIDE -- similar, but the endpoints of the chain will not be spread out\n" +
                            " * CHAIN_PACKED -- the elements of the chain will be packed together. The horizontal or vertical bias attribute of the child will then affect the positioning of the packed elements"),
            LayoutInformation(
                    layoutResourceId = R.layout.preview_chain_weighted,
                    thumbnailResourceId = R.drawable.thumb_chain_style,
                    title = "Chain: Weighted Width or Height",
                    description = "The default behavior of a chain is to spread the elements equally in the available space. If one or more " +
                            "elements are using MATCH_CONSTRAINT (0dp), they will use the available empty space (equally divided among " +
                            "themselves). The attribute `layout_constraintHorizontal_weight` and " +
                            "`layout_constraintVertical_weight` will control how the space will be distributed among the elements " +
                            "using MATCH_CONSTRAINT." +
                            "\n\n" +
                            "For example, on a chain containing two elements using MATCH_CONSTRAINT, with " +
                            "the first element using a weight of 2 and the second a weight of 1, the space occupied by the first element" +
                            "will be twice that of the second element."),
            LayoutInformation(
                    layoutResourceId = R.layout.preview_dimension_ratio,
                    thumbnailResourceId = R.drawable.thumb_dimension_ratio,
                    title = "Dimension: Ratio",
                    description = "You can define one dimension of a widget as a ratio of the other one. In order to do that, " +
                            "you need to have at least one constrained dimension be set to 0dp (i.e., MATCH_CONSTRAINT), " +
                            "and set the attribute layout_constraintDimensionRatio to a given ratio." +
                            "\n\n" +
                            "The ratio can be expressed either as:\n" +
                            "\n * a float value, representing a ratio between width and height" +
                            "\n * a ratio in the form \"width:height\", for example: `layout_constraintDimensionRatio=\"16:9\"`"),

            /*
             * https://developer.android.com/reference/android/support/constraint/ConstraintLayout#DimensionConstraints
             */
            LayoutInformation(
                    layoutResourceId = R.layout.preview_dimension_min_max,
                    thumbnailResourceId = R.drawable.thumb_dimension_min_max,
                    title = "Dimension: Min & Max (width/height)",
                    description = "You can define minimum and maximum sizes for the ConstraintLayout itself:\n\n" +
                            "* Standard attributes can should be use - `minWidth`, `minHeight`, `maxWidth`, `maxHeight`.\n" +
                            "* Those minimum and maximum dimensions will be used by ConstraintLayout when its dimensions are set to `WRAP_CONTENT`"),

            LayoutInformation(
                    layoutResourceId = R.layout.preview_dimension_percent,
                    thumbnailResourceId = R.drawable.thumb_dimension_percentage,
                    title = "Dimension: Percent dimension",
                    description = "To use percent, you need to set the following:\n\n" +
                            "* The dimension should be set to MATCH_CONSTRAINT (0dp)\n" +
                            "* Then set the `layout_constraintWidth_percent` or `layout_constraintHeight_percent` attributes to a value between 0.0 and 1.0"),

            LayoutInformation(
                    layoutResourceId = R.layout.preview_virtual_helper_guideline,
                    thumbnailResourceId = R.drawable.thumb_virtual_helper_guideline,
                    title = "Virtual Helper: Guideline",
                    description = "The Guideline object allows you to create Horizontal and Vertical guidelines which " +
                            "are positioned relative to the ConstraintLayout container." +
                            "Widgets can then be positioned by constraining them to such guidelines. "),

            /*
             * https://developer.android.com/training/constraint-layout/index.html#constrain-to-a-barrier
             * https://constraintlayout.com/basics/barriers.html
             */
            LayoutInformation(
                    layoutResourceId = R.layout.preview_virtual_helper_barrier,
                    thumbnailResourceId = R.drawable.thumb_virtual_helper_barrier,
                    title = "Virtual Helper: Barrier",
                    description = "A Barrier references multiple widgets as input, and creates a virtual guideline " +
                            "based on the most extreme widget on the specified side."),

            /*
             * https://developer.android.com/reference/android/support/constraint/Group.html
             */
            LayoutInformation(
                    layoutResourceId = R.layout.preview_virtual_helper_group,
                    thumbnailResourceId = R.drawable.thumb_virtual_helper_group,
                    title = "Virtual Helper: Group",
                    description = "This class controls the visibility of a set of referenced widgets. " +
                            "Widgets are referenced by being added to a comma separated list of ids.\n\n" +
                            "For example you can link multiple views: `app:constraint_referenced_ids=\"viewId1,viewId2,viewId3\"` and control their visibility at once."),

            /*
             * This is a demo of movie details page using various features of constraint layout.
             *
             *          .-'-.
             *        /`     |__
             *      /`  _.--`-,-`
             *      '-|`   a '<-.   []
             *        \     _\__) \=`
             *         C_  `    ,_/
             *           | ;----'
             */
            LayoutInformation(
                    layoutResourceId = R.layout.demo_movie_details,
                    thumbnailResourceId = R.drawable.spider_verse_poster,
                    title = "Demo: Movie Details",
                    description = "A demo screen containing movie details. Rotate device to see constraints in action."),

            /*
             * This is a demo of TED Talk video playback screen.
             *   ___________________
             * )=|                 |     /
             *   |    TED Talks    |====||
             *   |                 |====||
             *   |                 |+    \
             *   -------------------
             *          (--)
             *         *    *
             *        *      *
             *       *        *
             *      *          *
             *     *            *
             */
            LayoutInformation(
                    layoutResourceId = R.layout.demo_ted_talk_playback,
                    thumbnailResourceId = R.drawable.ic_ted_talks_logo,
                    title = "Demo: TED Talk Preview Screen",
                    description = "A demo screen containing TED talks video playback screen with different controls."),

            /*
             * This is a demo of PIN code entry screen
             *
             *    ______     ______     ______     ______
             *   |      |   |      |   |      |   |      |
             *   |  **  |   |  **  |   |  **  |   |  **  |
             *   |______|   |______|   |______|   |______|
             */
            LayoutInformation(
                    layoutResourceId = R.layout.demo_pin_code_entry,
                    thumbnailResourceId = R.drawable.ic_pin_entry_preview,
                    title = "Demo: Secure PIN Code Entry",
                    description = "A demo screen showcasing PIN code entry screen for secured areas of the app."),

            /*
             * Additional resources to learn more about ConstraintLayout
             */
            LayoutInformation(
                    layoutResourceId = R.layout.activity_learning_resource,
                    thumbnailResourceId = R.drawable.ic_book_128dp,
                    title = "Additional Resources: Tech Talks & Blogs",
                    description = "External resources on constraint layout. Tech talks on YouTube or blogs.")


            /*
             Next item template (easy to copy and paste)
             LayoutInformation(
                    layoutResourceId = R.layout.XYZ,
                    thumbnailResourceId = R.drawable.XYZ,
                    title = "XYZ: XYZ",
                    description = "XYZ")
             */


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
     * @return The URL to load the layout blob file. For example: https://github.com/amardeshbd/android-constraint-layout-cheatsheet/blob/master/app/src/main/res/layout/preview_dimension_ratio.xml
     */
    fun getLayoutUrl(@LayoutRes layoutResourceId: Int): String {
        // Contains package name and layout name
        // For example: com.hossainkhan.android.demo:layout/preview_positioning_top_left
        val resourceName = resources.getResourceName(layoutResourceId)

        if (!resourceName.contains("layout")) {
            throw IllegalStateException("Only layout resource is allowed.")
        }

        // Returns fully qualified URL that can be viewed online. For example:
        // https://github.com/amardeshbd/android-constraint-layout-cheatsheet/blob/master/app/src/main/res/layout/preview_dimension_ratio.xml
        return AppConfig.GITHUB_BASE_URL.plus("/blob/master/app/src/main/res/") +
                resourceName.split(':')
                        .last().plus(".xml")
    }
}