package com.example.moviedb.view.presenter

import android.util.Log
import com.example.moviedb.R
import com.example.moviedb.network.TheMovieNetworkConnect
import com.example.moviedb.view.ui.profile.ProfileView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import retrofit2.HttpException

class ProfilePresenter : MvpPresenter<ProfileView>() {

    private val retrofit = TheMovieNetworkConnect.getAccount()
    private var accountDisp: Disposable? = null
    lateinit var apiKey: String
    lateinit var sessionId: String

    fun getAccount() {
        accountDisp = retrofit.getAccountId(apiKey, sessionId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.setName(
                    if (it.name.isNotEmpty())
                        it.name
                    else
                        it.username
                )

                if (it.avatar.tmdb.avatarPath.isNotEmpty())
                    viewState.setAvatar(it.avatar.tmdb.avatarPath)
            }, {
                Log.d("Error", it.message ?: "null")
            })
    }

    fun exitProfile() {
        val resp = TheMovieNetworkConnect.getAuth()
            .deleteSession(sessionId, apiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ viewState.exitProfile() },
                {
                    Log.d("ExitProfile", it.stackTraceToString())
                })
    }
}