package com.example.moviedb.view.presenter

import android.os.Bundle
import com.example.moviedb.view.ui.FavouriteFragment
import com.example.moviedb.view.ui.MoviesFragment
import com.example.moviedb.view.ui.activity.ActivityView
import com.example.moviedb.view.ui.auth.AuthFragment
import com.example.moviedb.view.ui.profile.ProfileFragment
import moxy.MvpPresenter

class ActivityPresenter : MvpPresenter<ActivityView>() {

    private val apiKey = "7a00b045a944e9396f766b8e2b906775"

    private val bundle = Bundle().apply {
        putString("apiKey", apiKey)
    }

    private val authFragment = AuthFragment().apply { arguments = bundle }
    private val moviesFragment = MoviesFragment().apply { arguments = bundle }
    private val profileFragment = ProfileFragment().apply { arguments = bundle }
    private val favouriteFragment = FavouriteFragment().apply { arguments = bundle }

    override fun onFirstViewAttach() = displayAuth()

    private fun displayAuth() = viewState.changeFragment(authFragment)
    fun displayMovies() = viewState.changeFragment(moviesFragment)
    fun displayFavourite() = viewState.changeFragment(favouriteFragment)
    fun displayProfile() = viewState.changeFragment(profileFragment)

    fun successAuth(sessionId: String) {
        bundle.putString("sessionId", sessionId)
        viewState.isVisibleBottomNavigation()
        displayMovies()
    }

    fun exitAuth() {
        bundle.remove("sessionId")
        viewState.isVisibleBottomNavigation()
        displayAuth()
    }
}