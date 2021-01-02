package com.example.moviedb.models

import com.squareup.moshi.Json

data class TMDB(
    @Json(name = "tmdb")
    val tmdb: Avatar
)