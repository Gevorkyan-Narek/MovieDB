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

data class TMDB(
    @Json(name = "tmdb")
    val tmdb: Avatar
)

data class Avatar(
    @Json(name = "avatar_path")
    val avatarPath: String
)
