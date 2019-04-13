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

package com.hossainkhan.android.demo.ui.functionaldemo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hossainkhan.android.demo.R

/**
 * A simple adapter container movie poster images.
 */
class MoviePosterAdapter : RecyclerView.Adapter<MoviePosterAdapter.PosterViewHolder>() {
    private val posterImageResourceIds = listOf(
            R.drawable.poster_lego_batman,
            R.drawable.poster_i2,
            R.drawable.poster_angry_birds,
            R.drawable.poster_lego_movie,
            R.drawable.poster_wreckit_ralph,
            R.drawable.poster_dragon3
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        // create a new view
        val posterView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_poster, parent, false) as ImageView

        return PosterViewHolder(posterView)
    }

    override fun getItemCount(): Int {
        return posterImageResourceIds.size
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        holder.posterView.setImageResource(posterImageResourceIds[position])
    }

    // Provide a reference to the views for each data item
    class PosterViewHolder(val posterView: ImageView) : RecyclerView.ViewHolder(posterView)
}