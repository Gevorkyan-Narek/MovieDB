package com.example.moviedb.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviedb.R
import com.example.moviedb.databinding.AuthFragmentBinding
import com.example.moviedb.models.Token
import com.example.moviedb.network.TheMovieNetworkConnect
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

class AuthFragment : Fragment() {

    private lateinit var binding: AuthFragmentBinding
    private var retrofit = TheMovieNetworkConnect.getInstance()

    private val apiKey = "7a00b045a944e9396f766b8e2b906775"
    private var requestToken = ""

    private var session: Disposable? = null
    private var sessionWithLogin: Disposable? = null
    private var token: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthFragmentBinding.inflate(inflater, container, false)
        createRequestToken()
        binding.enter.setOnClickListener {
            if (requestToken.isNotBlank())
                createSessionWithLogin()
        }
        return binding.root
    }

    private fun createSession(token: Token) {
        session = retrofit.createSession(
            token.requestToken,
            apiKey
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.mainFragment, MoviesFragment())
                        ?.commit()
                },
                {
                    Log.d("Session Error", it.message ?: "null")
                })
    }

    private fun createSessionWithLogin() {
        sessionWithLogin = retrofit.createSessionWithLogin(
            binding.loginInput.text.toString(),
            binding.passwordInput.text.toString(),
            requestToken,
            apiKey
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("DoOnSuccess", "Start")
                    createSession(it)
                },
                {
                    if (it is HttpException) {
                        when {
                            it.code() == 401 -> {
                                Log.d("HTTP Error", it.message ?: "no message")
                                binding.enter.apply {
                                    setBackgroundColor(
                                        resources.getColor(
                                            R.color.orange,
                                            activity?.theme
                                        )
                                    )
                                    setTextColor(resources.getColor(R.color.white, activity?.theme))
                                }
                                binding.errorOutput.setText(R.string.wrong_login_and_password)
                            }
                            it.code() == 404 -> {
                                Log.d("HTTP Error", it.message ?: "no message")
                                binding.enter.apply {
                                    setBackgroundColor(
                                        resources.getColor(
                                            R.color.orange,
                                            activity?.theme
                                        )
                                    )
                                    setTextColor(resources.getColor(R.color.white, activity?.theme))
                                }
                                binding.errorOutput.setText(R.string.something_wrong)
                            }
                            else -> {
                                Log.d("HTTP Error", it.message ?: "no message")
                                binding.enter.apply {
                                    setBackgroundColor(
                                        resources.getColor(
                                            R.color.orange,
                                            activity?.theme
                                        )
                                    )
                                    setTextColor(resources.getColor(R.color.white, activity?.theme))
                                }
                                binding.errorOutput.setText(R.string.something_wrong)
                            }
                        }
                    } else {
                        Log.d("DoOnError", "Not is")
                        Log.d("HTTP Error", it.message ?: "no message")
                        binding.enter.apply {
                            setBackgroundColor(
                                resources.getColor(
                                    R.color.orange,
                                    activity?.theme
                                )
                            )
                            setTextColor(resources.getColor(R.color.white, activity?.theme))
                        }
                        binding.errorOutput.setText(R.string.something_wrong)
                    }
                })
    }

    private fun createRequestToken() {
        token = retrofit.createRequestToken(apiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                requestToken = it.requestToken
            }, {
                Log.d("Error", it.localizedMessage ?: "null")
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        session?.dispose()
        sessionWithLogin?.dispose()
        token?.dispose()
    }
}