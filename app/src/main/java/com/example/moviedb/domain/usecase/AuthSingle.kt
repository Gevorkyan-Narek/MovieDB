package com.example.moviedb.domain.usecase

import com.example.moviedb.data.App
import com.example.moviedb.data.models.Account
import com.example.moviedb.data.models.Session
import com.example.moviedb.data.models.Token
import com.example.moviedb.data.remote.AuthorizationNetwork
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class AuthSingle @Inject constructor() {

    private val apiKey = "7a00b045a944e9396f766b8e2b906775"

    @Inject
    lateinit var retrofit: AuthorizationNetwork

    init {
        App.getRetrofitComponent().inject(this)
    }

    fun getAccount(sessionId: String): Single<Account> =
        retrofit.getAccountId(apiKey, sessionId)
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteSession(sessionId: String): Completable = retrofit.deleteSession(sessionId, apiKey)
        .observeOn(AndroidSchedulers.mainThread())

    fun createRequestToken(): Single<Token> =
        retrofit.createRequestToken(apiKey)
            .observeOn(AndroidSchedulers.mainThread())

    fun createSessionWithLogin(
        login: String,
        password: String,
        requestToken: String
    ): Single<Token> =
        retrofit.createSessionWithLogin(
            login,
            password,
            requestToken,
            apiKey
        ).observeOn(AndroidSchedulers.mainThread())


    fun createSession(requestToken: String): Single<Session> =
        retrofit.createSession(
            requestToken,
            apiKey
        ).observeOn(AndroidSchedulers.mainThread())
}