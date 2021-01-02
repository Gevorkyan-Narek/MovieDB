package com.example.moviedb.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class TheMovieNetworkConnect {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        private var moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private var retrofitInstance: Retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(BASE_URL)
            .build()

        fun getAuth(): AuthorizationNetwork = retrofitInstance.create(AuthorizationNetwork::class.java)
        fun getAccount(): ProfileNetwork = retrofitInstance.create(ProfileNetwork::class.java)
    }
}