package com.example.moviedb.presentation.ui.auth

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface AuthView: MvpView {

    fun error(message: Int)
    fun success()
}