package com.example.moviedb.data.di

import com.example.moviedb.domain.usecase.AuthSingle
import com.example.moviedb.domain.usecase.AuthUseCase
import com.example.moviedb.presentation.auth.AuthPresenter
import com.example.moviedb.presentation.profile.ProfilePresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class])
@Singleton
interface RetrofitComponent {

    fun inject(authSingle: AuthSingle)
}