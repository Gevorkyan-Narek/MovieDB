package com.example.moviedb.presentation.activity

import androidx.fragment.app.Fragment
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ActivityView: MvpView {

    @AddToEndSingle
    fun changeFragment(fragment: Fragment)

    @AddToEndSingle
    fun successAuth()

    @AddToEndSingle
    fun visibleBottomNavigation()

    @AddToEndSingle
    fun goneBottomNavigation()
}