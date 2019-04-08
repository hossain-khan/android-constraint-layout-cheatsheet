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

package com.hossainkhan.android.demo.ui.layoutpreview

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.hossainkhan.android.demo.R

/**
 * Activity showcasing how virtual guideline barrier changes when a view length changes within the barrier.
 *
 * See https://developer.android.com/reference/android/support/constraint/Barrier
 */
class LayoutGuidelineBarrierActivity : LayoutPreviewBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup additional view that is only available in this screen.
        val toggleButton = findViewById<Button>(R.id.toggle_label_text_size)
        val textLabel = findViewById<TextView>(R.id.text_label)

        toggleButton.setOnClickListener {
            if (textLabel.text == getString(R.string.barrier_label_text_small)) {
                textLabel.setText(R.string.barrier_label_text_long)
            } else {
                textLabel.setText(R.string.barrier_label_text_small)
            }
        }
    }
}
