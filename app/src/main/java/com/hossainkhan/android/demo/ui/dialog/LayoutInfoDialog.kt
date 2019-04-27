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

package com.hossainkhan.android.demo.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.hossainkhan.android.demo.R


/**
 * Bottom sheet dialog to show layout information.
 */
class LayoutInfoDialog : BottomSheetDialogFragment() {
    companion object {
        private const val BUNDLE_ARG_KEY_TITLE = "BUNDLE_TITLE"
        private const val BUNDLE_ARG_KEY_DESC = "BUNDLE_DESCRIPTION"

        fun newInstance(title: String, description: String): LayoutInfoDialog {
            val args = Bundle()
            args.putString(BUNDLE_ARG_KEY_TITLE, title)
            args.putString(BUNDLE_ARG_KEY_DESC, description)

            val dialog = LayoutInfoDialog()

            dialog.arguments = args

            return dialog
        }
    }


    lateinit var infoTitle: TextView
    lateinit var infoDescription: TextView
    lateinit var okButton: MaterialButton
    lateinit var previewXml: MaterialButton
    var previewXmlListener: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_layout_info_sheet, container, false)
        infoTitle = view.findViewById(R.id.layout_info_title)
        infoDescription = view.findViewById(R.id.layout_info_description)
        okButton = view.findViewById(R.id.layout_info_ok)
        previewXml = view.findViewById(R.id.layout_info_preview_xml)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Make the bottom sheet dialog expand to full height
        // Source: https://medium.com/@OguzhanAlpayli/bottom-sheet-dialog-fragment-expanded-full-height-65b725c8309
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val dialog = dialog as BottomSheetDialog
            val bottomSheet = dialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            val behavior = BottomSheetBehavior.from<View>(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = 0
        }

        bindView(
                arguments!!.getString(BUNDLE_ARG_KEY_TITLE, ""),
                arguments!!.getString(BUNDLE_ARG_KEY_DESC, "")
        )
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }


    private fun bindView(title: String, description: String) {
        infoTitle.text = title
        infoDescription.text = description

        okButton.setOnClickListener { dismiss() }
        previewXml.setOnClickListener {
            dismiss()
            previewXmlListener?.invoke()
        }
    }
}