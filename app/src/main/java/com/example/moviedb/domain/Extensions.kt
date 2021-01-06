package com.example.moviedb.domain

import android.widget.ImageView
import com.bumptech.glide.Glide

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