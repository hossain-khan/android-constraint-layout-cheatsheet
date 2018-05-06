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
import android.net.Uri
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.customtabs.CustomTabsIntent
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.andrognito.flashbar.Flashbar
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.data.AppDataStore
import com.hossainkhan.android.demo.data.LayoutInformation
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

/**
 * Relative positioning is one of the basic building block of creating layouts in ConstraintLayout.
 * Those constraints allow you to position a given widget relative to another one.
 * You can constrain a widget on the horizontal and vertical axis.
 */
class LayoutPositioningDemoActivity : AppCompatActivity() {

    companion object {
        private const val BUNDLE_KEY_LAYOUT_RESID = "KEY_LAYOUT_RESOURCE_ID"

        /**
         * Creates an intent with required information to start this activity.
         *
         * @param context Activity context.
         * @param layoutResourceId The layout resource ID to load into the view.
         */
        fun createStartIntent(context: Context, @LayoutRes layoutResourceId: Int): Intent {
            val intent = Intent(context, LayoutPositioningDemoActivity::class.java)
            intent.putExtra(BUNDLE_KEY_LAYOUT_RESID, layoutResourceId)
            return intent
        }
    }

    @Inject
    lateinit var appDataStore: AppDataStore

    lateinit var layoutInformation: LayoutInformation
    var flashbar: Flashbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val layoutResourceId = intent.getIntExtra(BUNDLE_KEY_LAYOUT_RESID, -1)
        val resourceName = appDataStore.layoutStore.getLayoutUrl(layoutResourceId)
        layoutInformation = appDataStore.layoutStore.layoutsInfos[layoutResourceId]!!
        Timber.d("Loading layout: %s, info: %s", resourceName, layoutInformation)

        setContentView(layoutResourceId)

        supportActionBar?.title = layoutInformation.title

        showLayoutInfo(layoutInformation)
    }

    /**
     * Loads layout information and previews in a snackbar.
     */
    private fun showLayoutInfo(layoutInformation: LayoutInformation, fromUser: Boolean = false) {
        if (flashbar == null) {
            flashbar = Flashbar.Builder(this)
                    .gravity(Flashbar.Gravity.BOTTOM)
                    .title(layoutInformation.title.toString())
                    .message(layoutInformation.description.toString())
                    .backgroundColorRes(R.color.colorPrimaryDark)
                    .positiveActionText(R.string.btn_cta_preview_layout_xml)
                    .negativeActionText(R.string.btn_cta_okay)
                    .positiveActionTextColorRes(R.color.colorAccent)
                    .negativeActionTextColorRes(R.color.colorAccent)
                    .positiveActionTapListener(object : Flashbar.OnActionTapListener {
                        override fun onActionTapped(bar: Flashbar) {
                            Timber.d("Loading the XML for ")
                            bar.dismiss()
                            loadLayoutUrl()
                        }
                    })
                    .negativeActionTapListener(object : Flashbar.OnActionTapListener {
                        override fun onActionTapped(bar: Flashbar) {
                            Timber.d("Closing dialog.")
                            bar.dismiss()
                        }
                    })
                    .build()
        }

        Timber.d("Flash bar showing: %s", flashbar?.isShown())
        if (flashbar?.isShown() == false) {
            if (fromUser || appDataStore.shouldshowLayoutInformation(layoutInformation.layoutResourceId)) {
                flashbar?.show()
            }
        } else {
            flashbar?.dismiss()
        }
    }

    /**
     * Loads currently running layout from Github into chrome web view.
     */
    fun loadLayoutUrl() {
        val layoutUrl = appDataStore.layoutStore.getLayoutUrl(layoutInformation.layoutResourceId)
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(false)
                .addDefaultShareMenuItem()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(layoutUrl))
    }


    //
    // Setup menu item on the action bar.
    //
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_layout_positioning, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.show_layout_info_menu_item -> {
                showLayoutInfo(layoutInformation, true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
