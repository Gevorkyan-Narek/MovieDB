package com.example.moviedb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.moviedb.databinding.ActivityMainBinding
import com.example.moviedb.models.Token
import com.example.moviedb.network.TheMovieNetworkConnect
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var retrofit = TheMovieNetworkConnect.getInstance()

    private val apiKey = "7a00b045a944e9396f766b8e2b906775"
    var requestToken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        createRequestToken()
        binding.enter.setOnClickListener {
            if (requestToken.isNotBlank())
                createSessionWithLogin()
        }
    }

    private fun createSession(token: Token) {
        retrofit.createSession(
            token.request_token,
            apiKey
        ).doOnSuccess {
            Log.d("Success", it.success.toString())
        }.doOnError {
            Log.d("Error", it.stackTraceToString())
        }.subscribe()
    }

    private fun createSessionWithLogin() {
        retrofit.createSessionWithLogin(
            binding.loginInput.text.toString(),
            binding.passwordInput.text.toString(),
            requestToken,
            apiKey
        )
            .doOnSuccess { createSession(it) }
            .doOnError {
                if (it is HttpException) {
                    Log.d("Error", it.response()?.raw()?.request()?.url().toString())
                }
            }.subscribe()
    }

    private fun createRequestToken() {
        retrofit.createRequestToken(apiKey)
            .doOnSuccess {
                requestToken = it.request_token
            }
            .doOnError {
                Log.d("Error", it.localizedMessage ?: "null")
            }
            .subscribe()
    }
}