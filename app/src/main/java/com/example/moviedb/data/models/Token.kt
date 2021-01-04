package com.example.moviedb.data.models

import com.squareup.moshi.Json

data class Token (
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "request_token")
    val requestToken: String,
    @Json(name = "status_message")
    val statusMessage: String? = null
)