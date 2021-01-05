package com.example.moviedb.data.di

import com.example.moviedb.domain.usecase.AuthUseCase
import com.example.moviedb.presentation.activity.ActivityPresenter
import com.example.moviedb.presentation.auth.AuthPresenter
import com.example.moviedb.presentation.profile.ProfilePresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AuthModule::class, RetrofitModule::class])
@Singleton
interface AuthComponent {

    fun inject(authPresenter: AuthPresenter)
    fun inject(profilePresenter: ProfilePresenter)
    fun inject(authUseCase: AuthUseCase)
    fun inject(activityPresenter: ActivityPresenter)
}