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

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.hossainkhan.android.demo.R
import com.hossainkhan.android.demo.ui.layoutpreview.LayoutPreviewBaseActivity
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Activity showcasing how visibility GONE affects constraints.
 *
 * See https://developer.android.com/reference/android/support/constraint/ConstraintLayout#VisibilityBehavior
 */
class MovieDetailsPreviewActivity : LayoutPreviewBaseActivity() {
    private val generalClickListener = View.OnClickListener { view ->
        Toast.makeText(view.context, "You tapped on ${view}", Toast.LENGTH_SHORT).show()

        // Some custom logic to make the UI alive!
        when (view.id) {
            R.id.rating_thumbs_up, R.id.rating_thumbs_down -> {
                applyColorTint((view as ImageButton), R.color.white)
            }
        }
    }


    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ratingThumbsUp = findViewById<ImageButton>(R.id.rating_thumbs_up)
        val ratingThumbsDown = findViewById<ImageButton>(R.id.rating_thumbs_down)
        val addToFav = findViewById<ImageButton>(R.id.rating_add_fav)
        val rentButton = findViewById<Button>(R.id.button_rent)
        val buyButton = findViewById<Button>(R.id.button_buy)
        val trailer = findViewById<Button>(R.id.movie_trailer)

        // Apply generic toast listener to touchable views so that user gets feedback when they tap it
        applyToastListener(ratingThumbsUp, ratingThumbsDown, addToFav, rentButton, buyButton)

        trailer.setOnClickListener {
            val youtubeTrailerUrl = "https://www.youtube.com/watch?v=g4Hbz2jLxvQ"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(youtubeTrailerUrl)))
        }

        recyclerView = findViewById(R.id.movie_related_contents)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MovieDetailsPreviewActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = MoviePosterAdapter()
        }
    }

    private fun applyToastListener(vararg views: View) {
        views.forEach {
            it.setOnClickListener(generalClickListener)
        }
    }

    private fun applyColorTint(view: ImageView, @ColorRes tintColor: Int) {
        ImageViewCompat.setImageTintList(view,
                ColorStateList.valueOf(ContextCompat.getColor(this.applicationContext, tintColor))
        )
    }
}
