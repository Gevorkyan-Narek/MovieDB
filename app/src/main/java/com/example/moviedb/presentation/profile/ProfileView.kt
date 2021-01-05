package com.example.moviedb.presentation.profile

import com.example.moviedb.data.models.Account
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ProfileView: MvpView {

    @AddToEndSingle
    fun exitProfile()

    @AddToEndSingle
    fun getProfile(account: Account)

    @AddToEndSingle
    fun visibleLoading()

    @AddToEndSingle
    fun goneLoading()
}