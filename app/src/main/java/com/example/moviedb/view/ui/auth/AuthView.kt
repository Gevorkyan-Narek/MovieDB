package com.example.moviedb.view.ui.auth

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface AuthView: MvpView {

    fun error(message: Int)
    fun success(sessionId: String)
}