package com.example.moviedb.domain.usecase

import com.example.moviedb.data.models.Account

interface ProfileCallback {

    fun getAccount(account: Account)

    fun exitAccount()
}