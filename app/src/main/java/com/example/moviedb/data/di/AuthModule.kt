package com.example.moviedb.data.di

import com.example.moviedb.domain.usecase.AuthSingle
import com.example.moviedb.domain.usecase.AuthUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthModule {

    @Provides
    @Singleton
    fun provideAuthUseCase(): AuthUseCase = AuthUseCase()

    @Provides
    @Singleton
    fun provideAuthSingle(): AuthSingle = AuthSingle()
}