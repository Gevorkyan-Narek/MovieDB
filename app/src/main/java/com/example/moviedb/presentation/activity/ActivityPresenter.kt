package com.example.moviedb.presentation.activity

import com.example.moviedb.data.App
import com.example.moviedb.domain.usecase.AuthUseCase
import com.example.moviedb.presentation.FavouriteFragment
import com.example.moviedb.presentation.MoviesFragment
import com.example.moviedb.presentation.auth.AuthFragment
import com.example.moviedb.presentation.profile.ProfileFragment
import moxy.MvpPresenter
import javax.inject.Inject

class ActivityPresenter : MvpPresenter<ActivityView>() {

    @Inject
    lateinit var authUseCase: AuthUseCase

    private val authFragment = AuthFragment()
    private val moviesFragment = MoviesFragment()
    private val profileFragment = ProfileFragment()
    private val favouriteFragment = FavouriteFragment()

    override fun onFirstViewAttach()  {
        App.getAuthComponent().inject(this)
        displayAuth()
    }

    private fun displayAuth() = viewState.changeFragment(authFragment)
    fun displayMovies() = viewState.changeFragment(moviesFragment)
    fun displayFavourite() = viewState.changeFragment(favouriteFragment)
    fun displayProfile() = viewState.changeFragment(profileFragment)

    fun successAuth() {
        viewState.visibleBottomNavigation()
        viewState.successAuth()
    }

    fun exitAuth() {
        viewState.goneBottomNavigation()
        displayAuth()
    }

    override fun onDestroy() {
        super.onDestroy()
        authUseCase.disposeAll()
    }
}