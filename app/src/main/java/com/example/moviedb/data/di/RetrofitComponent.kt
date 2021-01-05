package com.example.moviedb.data.di

import com.example.moviedb.domain.usecase.AuthSingle
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class])
@Singleton
interface RetrofitComponent {

    fun inject(authSingle: AuthSingle)
}