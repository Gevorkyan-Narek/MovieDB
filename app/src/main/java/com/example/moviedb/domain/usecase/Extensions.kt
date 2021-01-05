package com.example.moviedb.domain.usecase

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.setAvatar(avatar: String) {
    Glide.with(context)
        .load("https://image.tmdb.org/t/p/w500$avatar")
        .circleCrop()
        .into(this)
}

fun ImageView.setAvatar(avatar: Int) {
    Glide.with(context)
        .load(avatar)
        .circleCrop()
        .into(this)
}