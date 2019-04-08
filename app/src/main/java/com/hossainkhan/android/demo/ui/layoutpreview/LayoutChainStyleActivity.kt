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
import android.widget.RadioButton
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.*
import androidx.transition.TransitionManager
import com.hossainkhan.android.demo.R
import timber.log.Timber

/**
 * Showcases the constraint layout chaining with different styles.
 *
 * https://developer.android.com/reference/android/support/constraint/ConstraintLayout#Chains
 */
class LayoutChainStyleActivity : LayoutPreviewBaseActivity() {

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var guideTextView: TextView
    /**
     * Use constraint set to dynamically update constraints
     * https://developer.android.com/reference/android/support/constraint/ConstraintSet
     */
    private val constraintSet = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        constraintLayout = findViewById(R.id.constraint_layout_root)
        guideTextView = findViewById(R.id.view_chain_horizontal_guide_text)
        constraintSet.clone(constraintLayout)
    }

    fun onRadioButtonClicked(view: View) {
        val checked = (view as RadioButton).isChecked

        // Check which radio button was clicked
        when (view.getId()) {
            R.id.radio_chain_action_packed -> {
                applyChainStyle(checked, R.string.view_guide_chain_style_packed, CHAIN_PACKED)
            }
            R.id.radio_chain_action_spread -> {
                applyChainStyle(checked, R.string.view_guide_chain_style_spread, CHAIN_SPREAD)
            }
            R.id.radio_chain_action_spread_inside -> {
                applyChainStyle(checked, R.string.view_guide_chain_style_spread_inside, CHAIN_SPREAD_INSIDE)
            }
        }
    }

    private fun applyChainStyle(isChecked: Boolean, @StringRes guideText: Int, chainStyle: Int) {
        if (isChecked) {
            Timber.d("Updating chain style to %s, and text to %s", chainStyle, getString(guideText))
            guideTextView.setText(guideText)
            TransitionManager.beginDelayedTransition(constraintLayout)
            constraintSet.setHorizontalChainStyle(R.id.view_chain_view_first, chainStyle)
            constraintSet.applyTo(constraintLayout)
        } else {
            Timber.i("View was not checked. Not taking action.")
        }
    }
}
