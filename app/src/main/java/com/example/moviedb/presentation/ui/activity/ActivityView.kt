package com.example.moviedb.presentation.ui.activity

import androidx.fragment.app.Fragment
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ActivityView: MvpView {

    @AddToEndSingle
    fun changeFragment(fragment: Fragment)

    @AddToEndSingle
    fun successAuth()
}