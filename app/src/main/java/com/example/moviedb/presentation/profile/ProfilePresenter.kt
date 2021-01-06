package com.example.moviedb.presentation.profile

import com.example.moviedb.data.App
import com.example.moviedb.data.models.Account
import com.example.moviedb.domain.AccountCallback
import com.example.moviedb.domain.usecase.AuthUseCase
import moxy.MvpPresenter
import javax.inject.Inject

class ProfilePresenter : MvpPresenter<ProfileView>(), AccountCallback {

    @Inject
    lateinit var authUseCase: AuthUseCase

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.getAuthComponent().inject(this)
        openProfile()
    }

    private fun openProfile() {
        viewState.visibleLoading()
        authUseCase.getAccount(this)
    }

    fun exitProfile() = authUseCase.deleteSession(this)

    override fun getAccount(account: Account) {
        viewState.getProfile(account)
        viewState.goneLoading()
    }
    override fun exitAccount() = viewState.exitProfile()

}