package com.example.moviedb.presentation.presenters

import com.example.moviedb.domain.usecase.AuthUseCase
import com.example.moviedb.presentation.ui.FavouriteFragment
import com.example.moviedb.presentation.ui.MoviesFragment
import com.example.moviedb.presentation.ui.activity.ActivityView
import com.example.moviedb.presentation.ui.auth.AuthFragment
import com.example.moviedb.presentation.ui.profile.ProfileFragment
import moxy.MvpPresenter
import javax.inject.Inject

class ActivityPresenter : MvpPresenter<ActivityView>() {

    @Inject
    lateinit var authUseCase: AuthUseCase

    private val authFragment = AuthFragment()
    private val moviesFragment = MoviesFragment()
    private val profileFragment = ProfileFragment()
    private val favouriteFragment = FavouriteFragment()

    override fun onFirstViewAttach() = displayAuth()

    private fun displayAuth() = viewState.changeFragment(authFragment)
    fun displayMovies() = viewState.changeFragment(moviesFragment)
    fun displayFavourite() = viewState.changeFragment(favouriteFragment)
    fun displayProfile() = viewState.changeFragment(profileFragment)

    fun successAuth() {
        viewState.successAuth()
    }

    fun exitAuth() {
        viewState.successAuth()
        displayAuth()
    }

    override fun onDestroy() {
        super.onDestroy()
        authUseCase.disposeAll()
    }
}