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

package com.hossainkhan.android.demo.ui.resource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.data.ResourceInfo
import com.hossainkhan.android.demo.databinding.ListItemResourceTechTalkBinding
import com.hossainkhan.android.demo.ui.common.DataBoundListAdapter

class ResourceListAdapter(
        private val itemClickCallback: ((ResourceInfo) -> Unit)?
) : DataBoundListAdapter<ResourceInfo, ListItemResourceTechTalkBinding>(
        diffCallback = object : DiffUtil.ItemCallback<ResourceInfo>() {
            override fun areItemsTheSame(oldItem: ResourceInfo, newItem: ResourceInfo): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: ResourceInfo, newItem: ResourceInfo): Boolean {
                return oldItem == newItem
            }
        }
) {

    override fun createBinding(parent: ViewGroup): ListItemResourceTechTalkBinding {
        val binding = DataBindingUtil.inflate<ListItemResourceTechTalkBinding>(
                LayoutInflater.from(parent.context), R.layout.list_item_resource_tech_talk,
                parent, false
        )

        binding.actionPlay.setOnClickListener {
            binding.data?.let {
                itemClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ListItemResourceTechTalkBinding, item: ResourceInfo) {
        binding.data = item
    }
}