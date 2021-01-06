package com.example.moviedb.domain.usecase

import android.util.Log
import com.example.moviedb.data.App
import com.example.moviedb.domain.AccountCallback
import com.example.moviedb.domain.AuthCallback
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AuthUseCase @Inject constructor() {

    private lateinit var sessionId: String

    @Inject
    lateinit var authSingle: AuthSingle

    private val disposeArray = mutableListOf<Disposable>()

    init {
        App.getAuthComponent().inject(this)
    }

    fun getAccount(accountCallback: AccountCallback) {
        authSingle.getAccount(sessionId)
            .subscribe(
                { accountCallback.getAccount(it) },
                { Log.d("Error", it.message ?: "null") }
            ).apply { disposeArray.add(this) }
    }

    fun deleteSession(accountCallback: AccountCallback) {
        authSingle.deleteSession(sessionId)
            .subscribe(
                { accountCallback.exitAccount() },
                { Log.d("ExitProfile", it.stackTraceToString()) }
            ).apply { disposeArray.add(this) }
    }

    fun authorize(login: String, password: String, authCallback: AuthCallback) =
        createRequestToken(login, password, authCallback)

    private fun createRequestToken(login: String, password: String, authCallback: AuthCallback) {
        authSingle.createRequestToken()
            .subscribe(
                {
                    createSessionWithLogin(login, password, authCallback, it.requestToken)
                },
                { authCallback.error(it) }
            ).apply { disposeArray.add(this) }
    }

    private fun createSessionWithLogin(
        login: String,
        password: String,
        authCallback: AuthCallback,
        requestToken: String
    ) {
        authSingle.createSessionWithLogin(
            login,
            password,
            requestToken
        ).subscribe(
            { createSession(it.requestToken, authCallback) },
            { authCallback.error(it) }
        ).apply { disposeArray.add(this) }
    }

    private fun createSession(requestToken: String, authCallback: AuthCallback) {
        authSingle.createSession(
            requestToken
        ).subscribe(
            {
                sessionId = it.sessionId
                authCallback.success()
            },
            { authCallback.error(it) }
        ).apply { disposeArray.add(this) }
    }

    fun disposeAll() {
        disposeArray.forEach { disposable -> disposable.dispose() }
    }
}