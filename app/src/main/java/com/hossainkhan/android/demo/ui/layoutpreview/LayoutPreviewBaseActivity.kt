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

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.NavUtils
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.data.LayoutInformation
import com.hossainkhan.android.demo.ui.dialog.LayoutInfoDialog
import com.hossainkhan.android.demo.viewmodel.LayoutPreviewViewModelFactory
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

/**
 * This the the **base** activity for previewing different constraint layout features.
 *
 * Currently, the activity does following basic actions:
 * 1. Inject necessary dependencies required for all activities.
 * 1. Load and populate [LayoutInformation] for currently viewing layout.
 * 1. Shows tooltip with layout details for the first time only.
 * 1. Loads layout XML source code from Github.
 */
open class LayoutPreviewBaseActivity : AppCompatActivity() {

    companion object {
        internal const val BUNDLE_KEY_LAYOUT_RESID = "KEY_LAYOUT_RESOURCE_ID"

        /**
         * Creates an intent with required information to start this activity.
         *
         * @param context Activity context.
         * @param layoutResourceId The layout resource ID to load into the view.
         */
        fun createStartIntent(context: Context, @LayoutRes layoutResourceId: Int): Intent {
            val intent = Intent(context, LayoutPreviewBaseActivity::class.java)
            intent.putExtra(BUNDLE_KEY_LAYOUT_RESID, layoutResourceId)
            return intent
        }

        /**
         * Creates an intent with required information to start child activity with specific interactive demo.
         *
         * @param context Activity context.
         * @param clazz Activity class that has interactive demo.
         * @param layoutResourceId The layout resource ID to load into the view.
         */
        fun <T> createStartIntent(context: Context, clazz: Class<T>, @LayoutRes layoutResourceId: Int): Intent {
            val intent = Intent(context, clazz)
            intent.putExtra(BUNDLE_KEY_LAYOUT_RESID, layoutResourceId)
            return intent
        }
    }

    @Inject
    internal lateinit var viewModelFactory: LayoutPreviewViewModelFactory

    private lateinit var viewModel: LayoutInfoViewModel

    internal var infoDialog: LayoutInfoDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val layoutResourceId = intent.getIntExtra(BUNDLE_KEY_LAYOUT_RESID, -1)

        setContentView(layoutResourceId)

        viewModel = ViewModelProvider(this, viewModelFactory).get(LayoutInfoViewModel::class.java)
        viewModel.init(layoutResourceId)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.layoutInformation.observe(this,
                Observer { layoutInfo ->
                    Timber.d("Received layout info from LiveData: %s", layoutInfo)
                    updateActionBar(layoutInfo!!)
                    showLayoutInfo(layoutInfo)
                })
    }

    private fun updateActionBar(layoutInformation: LayoutInformation) {
        supportActionBar?.title = layoutInformation.title
    }

    /**
     * Loads layout information and previews in a snackbar.
     */
    private fun showLayoutInfo(layoutInformation: LayoutInformation, fromUser: Boolean = false) {
        infoDialog = LayoutInfoDialog.newInstance(
                layoutInformation.title.toString(),
                layoutInformation.description.toString()
        )
        infoDialog?.previewXmlListener = { loadLayoutUrl() }

        Timber.d("Layout info showing: %s", infoDialog?.isVisible)
        if (infoDialog?.isVisible == false) {
            if (fromUser || viewModel.isFirstTime) {
                infoDialog?.let {
                    it.show(supportFragmentManager, "dialog")
                }
            }
        } else {
            infoDialog?.dismiss()
        }
    }

    /**
     * Loads currently running layout from Github into chrome web view.
     */
    fun loadLayoutUrl() {
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(false)
                .addDefaultShareMenuItem()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(viewModel.layoutUrl))
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
                showLayoutInfo(viewModel.layoutInformation.value!!, true)
                true
            }
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button
                // https://developer.android.com/training/implementing-navigation/ancestral
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
