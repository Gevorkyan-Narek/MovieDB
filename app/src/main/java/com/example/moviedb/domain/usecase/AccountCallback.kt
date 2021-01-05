package com.example.moviedb.domain.usecase

import com.example.moviedb.data.models.Account

interface AccountCallback {

    fun getAccount(account: Account)
    fun exitAccount()
}