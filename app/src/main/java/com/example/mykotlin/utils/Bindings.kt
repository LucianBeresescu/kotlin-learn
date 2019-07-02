package com.example.mykotlin.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mykotlin.BuildConfig


@BindingAdapter("bind:imageUrl")
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(context)
            .load(BuildConfig.SMALL_IMAGE_URL + imageUrl)
            .into(this)
}
