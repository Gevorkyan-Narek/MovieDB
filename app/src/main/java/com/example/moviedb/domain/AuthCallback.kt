package com.example.moviedb.domain

interface AuthCallback {

    fun success()
    fun error(error: Throwable)
}