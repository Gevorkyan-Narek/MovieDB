package com.example.moviedb

import android.app.Application
import com.example.moviedb.data.di.AuthComponent
import com.example.moviedb.data.di.DaggerAuthComponent
import com.example.moviedb.data.di.DaggerRetrofitComponent
import com.example.moviedb.data.di.RetrofitComponent

class App : Application() {

    companion object {
        private lateinit var retrofit: RetrofitComponent
        fun getComponent(): RetrofitComponent = retrofit

        private lateinit var auth: AuthComponent
        fun getComponentAuth(): AuthComponent = auth
    }

    override fun onCreate() {
        super.onCreate()
        retrofit = buildComponent()
        auth = buildAuth()
    }

    private fun buildComponent(): RetrofitComponent = DaggerRetrofitComponent.builder().build()

    private fun buildAuth(): AuthComponent = DaggerAuthComponent.builder().build()
}