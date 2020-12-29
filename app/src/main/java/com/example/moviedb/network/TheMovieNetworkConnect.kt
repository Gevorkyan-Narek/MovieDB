package com.example.moviedb.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
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

        fun getInstance(): MovieDBInterface = retrofitInstance.create(MovieDBInterface::class.java)
    }
}