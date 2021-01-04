package com.example.moviedb.domain.usecase

import android.util.Log
import com.example.moviedb.App
import com.example.moviedb.data.models.Account
import com.example.moviedb.data.models.Session
import com.example.moviedb.data.models.Token
import com.example.moviedb.data.remote.AuthorizationNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AuthUseCase @Inject constructor() {

    private val apiKey = "7a00b045a944e9396f766b8e2b906775"
    private var requestToken = ""
    lateinit var session: Session

    @Inject
    lateinit var retrofit: AuthorizationNetwork

    private val disposeArray = mutableListOf<Disposable>()

    init {
        App.getComponent().inject(this)
    }

    fun getAccount(profileCallback: ProfileCallback) {
        retrofit.getAccountId(apiKey, session.sessionId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                profileCallback.getAccount(it)
            }, {
                Log.d("Error", it.message ?: "null")
            }).apply {
                disposeArray.add(this)
            }
    }

    fun deleteSession(profileCallback: ProfileCallback) {
        retrofit.deleteSession(session.sessionId, apiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                profileCallback.exitAccount()
            }, {
                Log.d("ExitProfile", it.stackTraceToString())
            }).apply {
                disposeArray.add(this)
            }.apply {
                disposeArray.add(this)
            }
    }

    fun authorize(login: String, password: String, authCallback: AuthCallback) =
        createRequestToken(login, password, authCallback)

    private fun createRequestToken(login: String, password: String, authCallback: AuthCallback) {
        retrofit.createRequestToken(apiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                requestToken = it.requestToken
                createSessionWithLogin(login, password, authCallback)
            }, {
                authCallback.error(it)
            }).apply {
                disposeArray.add(this)
            }
    }

    private fun createSessionWithLogin(
        login: String,
        password: String,
        authCallback: AuthCallback
    ) {
        retrofit.createSessionWithLogin(
            login,
            password,
            requestToken,
            apiKey
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    createSession(it, authCallback)
                },
                {
                    authCallback.error(it)
                }).apply {
                disposeArray.add(this)
            }
    }

    private fun createSession(token: Token, authCallback: AuthCallback) {
        retrofit.createSession(
            token.requestToken,
            apiKey
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    session = it
                    authCallback.success()
                },
                {
                    authCallback.error(it)
                }).apply {
                disposeArray.add(this)
            }
    }

    fun disposeAll() {
        disposeArray.forEach { disposable -> disposable.dispose() }
    }
}