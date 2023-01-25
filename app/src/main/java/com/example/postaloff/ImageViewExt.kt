package com.example.postaloff

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this)
        .load(imageUrl)
        .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher))
        .centerCrop().into(this)
}