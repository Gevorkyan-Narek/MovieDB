package com.example.moviedb.data.remote

import com.example.moviedb.data.models.Account
import com.example.moviedb.data.models.Session
import com.example.moviedb.data.models.Token
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface AuthorizationNetwork {

    @GET("authentication/token/new")
    fun createRequestToken(@Query("api_key") apiKey: String): Single<Token>

    @FormUrlEncoded
    @POST("authentication/token/validate_with_login")
    fun createSessionWithLogin(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("request_token") requestToken: String,
        @Query("api_key") apiKey: String
    ): Single<Token>

    @FormUrlEncoded
    @POST("authentication/session/new")
    fun createSession(
        @Field("request_token") requestToken: String,
        @Query("api_key") apiKey: String
    ): Single<Session>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    fun deleteSession(
        @Field("session_id") sessionId: String,
        @Query("api_key") apiKey: String
    ) : Completable

    @GET("account")
    fun getAccountId(@Query("api_key") apiKey: String, @Query("session_id") sessionId: String): Single<Account>
}