package com.example.moviedb.presentation.ui.profile

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ProfileView: MvpView {

    @AddToEndSingle
    fun setName(name: String)

    @AddToEndSingle
    fun setAvatar(avatar: String? = null)

    @AddToEndSingle
    fun exitProfile()
}