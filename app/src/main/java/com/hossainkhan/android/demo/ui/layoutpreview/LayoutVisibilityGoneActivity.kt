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

package com.hossainkhan.android.demo.ui.layoutpreview

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.hossainkhan.android.demo.R

/**
 * Activity showcasing how visibility GONE affects constraints.
 *
 * See https://developer.android.com/reference/android/support/constraint/ConstraintLayout#VisibilityBehavior
 */
class LayoutVisibilityGoneActivity : LayoutPreviewBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup additional view that is only available in this screen.
        val toggleButton = findViewById<Button>(R.id.toggle_view_visibility_button)
        val firstView = findViewById<View>(R.id.visibility_behaviour_box_start)

        toggleButton.setOnClickListener {
            when (firstView.visibility) {
                View.VISIBLE -> firstView.visibility = View.GONE
                else -> firstView.visibility = View.VISIBLE
            }
        }
    }


}
