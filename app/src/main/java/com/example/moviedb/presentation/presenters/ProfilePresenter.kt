package com.example.moviedb.presentation.presenters

import com.example.moviedb.App
import com.example.moviedb.data.models.Account
import com.example.moviedb.domain.usecase.AuthUseCase
import com.example.moviedb.domain.usecase.ProfileCallback
import com.example.moviedb.presentation.ui.profile.ProfileView
import moxy.MvpPresenter
import javax.inject.Inject

class ProfilePresenter : MvpPresenter<ProfileView>(), ProfileCallback {

    @Inject
    lateinit var authUseCase: AuthUseCase

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.getComponentAuth().inject(this)
    }

    fun openProfile() = authUseCase.getAccount(this)
    fun exitProfile() = authUseCase.deleteSession(this)

    override fun getAccount(account: Account) {
        viewState.setName(
            if (account.name.isNotEmpty())
                account.name
            else
                account.username
        )

        if (account.avatar.tmdb.avatarPath.isNotEmpty())
            viewState.setAvatar(account.avatar.tmdb.avatarPath)
    }

    override fun exitAccount() {
        viewState.exitProfile()
    }

}