package com.example.moviedb.view.presenter

import android.util.Log
import com.example.moviedb.R
import com.example.moviedb.models.Token
import com.example.moviedb.network.TheMovieNetworkConnect
import com.example.moviedb.view.ui.auth.AuthView
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import retrofit2.HttpException

class AuthPresenter : MvpPresenter<AuthView>() {

    private var retrofit = TheMovieNetworkConnect.getAuth()

    lateinit var apiKey: String

    private var requestToken = ""

    private fun createRequestToken() {

        val token = retrofit.createRequestToken(apiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                requestToken = it.requestToken
            }, {
                if (it is HttpException) {
                    Log.d("Token Error ${it.code()}", it.message ?: "null")
                    viewState.error(R.string.something_wrong)
                } else {
                    viewState.error(R.string.check_internet_connection)
                }
            })
    }

    fun createSessionWithLogin(login: String, password: String) {
        createRequestToken()
        val sessionWithLogin = retrofit.createSessionWithLogin(
            login,
            password,
            requestToken,
            apiKey
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    createSession(it)
                },
                {
                    if (it is HttpException) {
                        when {
                            it.code() == 401 -> {
                                Log.d("HTTP Error", it.message ?: "no message")

                                viewState.error(R.string.wrong_login_and_password)
                            }
                            it.code() == 404 -> {
                                Log.d("HTTP Error", it.message ?: "no message")

                                viewState.error(R.string.something_wrong)
                            }
                            else -> {
                                Log.d("HTTP Error", it.message ?: "no message")

                                viewState.error(R.string.something_wrong)
                            }
                        }
                    } else {
                        viewState.error(R.string.check_internet_connection)
                    }
                })
    }

    private fun createSession(token: Token) {
        val session = retrofit.createSession(
            token.requestToken,
            apiKey
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.success(it.sessionId)
                },
                {
                    viewState.error(R.string.check_internet_connection)
                })
    }
}