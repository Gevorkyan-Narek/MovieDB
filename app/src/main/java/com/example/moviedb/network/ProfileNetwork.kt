package com.example.moviedb.network

import com.example.moviedb.models.Account
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileNetwork {

    @GET("account")
    fun getAccountId(@Query("api_key") apiKey: String, @Query("session_id") sessionId: String): Single<Account>
}