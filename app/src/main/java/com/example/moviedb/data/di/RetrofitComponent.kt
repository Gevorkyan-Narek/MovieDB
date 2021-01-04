package com.example.moviedb.data.di

import com.example.moviedb.data.di.RetrofitModule
import com.example.moviedb.domain.usecase.AuthUseCase
import com.example.moviedb.presentation.presenters.AuthPresenter
import com.example.moviedb.presentation.presenters.ProfilePresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class])
@Singleton
interface RetrofitComponent {

    fun inject(authPresenter: AuthPresenter)
    fun inject(profilePresenter: ProfilePresenter)
    fun inject(authUseCase: AuthUseCase)
}