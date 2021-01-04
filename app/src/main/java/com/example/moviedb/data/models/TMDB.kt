package com.example.moviedb.data.models

import com.squareup.moshi.Json

data class TMDB(
    @Json(name = "tmdb")
    val tmdb: Avatar
)