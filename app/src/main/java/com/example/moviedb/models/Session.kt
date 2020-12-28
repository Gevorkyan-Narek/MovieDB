package com.example.moviedb.models

import com.squareup.moshi.Json

data class Session(
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "session_id")
    val sessionId: String
)