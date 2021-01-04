package com.example.moviedb.data.di

import com.example.moviedb.presentation.presenters.AuthPresenter
import com.example.moviedb.presentation.presenters.ProfilePresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AuthModule::class, RetrofitModule::class])
@Singleton
interface AuthComponent {

    fun inject(authPresenter: AuthPresenter)
    fun inject(profilePresenter: ProfilePresenter)
}