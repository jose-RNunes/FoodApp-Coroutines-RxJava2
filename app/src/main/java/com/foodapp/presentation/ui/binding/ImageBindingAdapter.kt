package com.foodapp.presentation.ui.binding


import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter


@BindingAdapter("loadUrl")
fun ImageView.loadUrl(loadUrl: String) {
    Glide.with(this.context)
        .load(loadUrl)
        .into(this)
}