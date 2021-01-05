package com.example.moviedb.presentation.auth

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface AuthView: MvpView {

    fun fail(message: Int)
    fun success()
}