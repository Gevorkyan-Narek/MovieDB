package com.example.moviedb.presentation.auth

import com.example.moviedb.data.App
import com.example.moviedb.R
import com.example.moviedb.domain.usecase.AuthCallback
import com.example.moviedb.domain.usecase.AuthUseCase
import moxy.MvpPresenter
import retrofit2.HttpException
import javax.inject.Inject

class AuthPresenter : MvpPresenter<AuthView>(), AuthCallback {

    @Inject
    lateinit var authUseCase: AuthUseCase

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.getAuthComponent().inject(this)
    }

    fun authorize(login: String, password: String) = authUseCase.authorize(login, password, this)

    override fun success() = viewState.success()

    override fun error(error: Throwable) {
        if (error is HttpException && error.code() == 401) {
            viewState.fail(R.string.wrong_login_and_password)
        } else {
            viewState.fail(R.string.something_wrong)
        }
    }
}