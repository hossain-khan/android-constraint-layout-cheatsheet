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

package com.hossainkhan.android.demo.layoutpositioning

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.hossainkhan.android.demo.data.LayoutDataStore
import timber.log.Timber

/**
 * Relative positioning is one of the basic building block of creating layouts in ConstraintLayout.
 * Those constraints allow you to position a given widget relative to another one.
 * You can constrain a widget on the horizontal and vertical axis.
 */
class PositioningHorizontalActivity : AppCompatActivity() {

    companion object {
        private const val BUNDLE_KEY_LAYOUT_RESID = "KEY_LAYOUT_RESOURCE_ID"

        fun createStartIntent(context: Context, @LayoutRes layoutResourceId: Int): Intent {
            val intent = Intent(context, PositioningHorizontalActivity::class.java)
            intent.putExtra(BUNDLE_KEY_LAYOUT_RESID, layoutResourceId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutResourceId = intent.getIntExtra(BUNDLE_KEY_LAYOUT_RESID, -1)
        val resourceName = resources.getResourceName(layoutResourceId)
        Timber.d("Loading layout: %s, info: %s",
                resourceName, LayoutDataStore().layoutsInfos[layoutResourceId])

        setContentView(layoutResourceId)
    }
}
