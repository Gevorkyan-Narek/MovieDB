package com.example.moviedb.data.models

import com.squareup.moshi.Json

data class Avatar(
    @Json(name = "avatar_path")
    val avatarPath: String
)