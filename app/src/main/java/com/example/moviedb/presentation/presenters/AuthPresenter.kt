package com.example.moviedb.presentation.presenters

import com.example.moviedb.App
import com.example.moviedb.R
import com.example.moviedb.domain.usecase.AuthCallback
import com.example.moviedb.domain.usecase.AuthUseCase
import com.example.moviedb.presentation.ui.auth.AuthView
import moxy.MvpPresenter
import retrofit2.HttpException
import javax.inject.Inject

class AuthPresenter : MvpPresenter<AuthView>(), AuthCallback {

    @Inject
    lateinit var authUseCase: AuthUseCase

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.getComponentAuth().inject(this)
    }

    fun authorize(login: String, password: String) = authUseCase.authorize(login, password, this)

    override fun success() = viewState.success()

    override fun error(error: Throwable) {
        if (error is HttpException && error.code() == 401) {
            viewState.error(R.string.wrong_login_and_password)
        } else {
            viewState.error(R.string.something_wrong)
        }
    }
}