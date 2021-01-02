package com.example.moviedb

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.setAvatar(avatar: String) {

    Glide.with(context)
        .load(avatar)
        .circleCrop()
        .into(this)
}

fun ImageView.setAvatar(avatar: Int) {

    Glide.with(context)
        .load(avatar)
        .circleCrop()
        .into(this)
}