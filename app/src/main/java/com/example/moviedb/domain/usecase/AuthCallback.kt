package com.example.moviedb.domain.usecase

interface AuthCallback {

    fun success()
    fun error(error: Throwable)
}