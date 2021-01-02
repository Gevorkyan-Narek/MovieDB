package com.example.moviedb.models

import com.squareup.moshi.Json

data class Account(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "username")
    val username: String,
    @Json(name = "avatar")
    val avatar: TMDB
)

