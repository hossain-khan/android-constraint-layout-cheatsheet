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

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.data.LayoutInformation

class LayoutBrowseAdapter(
        viewModel: LayoutBrowseViewModel,
        lifecycleOwner: LifecycleOwner,
        private val itemSelectedListener: (Int) -> Unit) : RecyclerView.Adapter<LayoutBrowseAdapter.ViewHolder>() {

    private var data: List<LayoutInformation> = emptyList()

    init {
        viewModel.layoutInfos.observe(lifecycleOwner, Observer {
            data = it!!
            notifyDataSetChanged()
        })
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(itemViewRoot: View,
                     private val onClickListener: (Int) -> Unit) : RecyclerView.ViewHolder(itemViewRoot) {
        val itemName = itemViewRoot.findViewById<TextView>(R.id.layout_preview_name)!!
        val itemThumb = itemViewRoot.findViewById<ImageView>(R.id.imageView)

        init {
            itemViewRoot.setOnClickListener {
                onClickListener(adapterPosition)
            }
        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_layout_preview, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return ViewHolder(itemView, this::itemClickHandler)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemName.text = data[position].title
        holder.itemThumb.setImageResource(data[position].thumbnailResourceId)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size

    private fun itemClickHandler(position: Int) {
        itemSelectedListener(data[position].layoutResourceId)
    }

}
