package com.example.moviedb.network

import com.example.moviedb.models.Session
import com.example.moviedb.models.Token
import io.reactivex.Completable
import io.reactivex.Maybe
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
    ): Maybe<Token>

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
}