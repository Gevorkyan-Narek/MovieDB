package com.example.moviedb.models

import com.squareup.moshi.Json

data class Token (
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "request_token")
    val request_token: String
)