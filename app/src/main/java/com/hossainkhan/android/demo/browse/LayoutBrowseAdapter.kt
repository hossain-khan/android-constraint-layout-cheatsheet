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

package com.hossainkhan.android.demo.browse

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.data.LayoutInformation

class LayoutBrowseAdapter(
        private val context: Context,
        private val data: List<LayoutInformation>,
        itemSelectedListener: (Int) -> Unit) : RecyclerView.Adapter<LayoutBrowseAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(val itemViewRoot: View) : RecyclerView.ViewHolder(itemViewRoot) {
        val itemName = itemViewRoot.findViewById<TextView>(R.id.layout_preview_name)!!
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_layout_preview, parent, false)
        // set the view's size, margins, paddings and layout parameters


        return ViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemName.text = data[position].title
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size

}
