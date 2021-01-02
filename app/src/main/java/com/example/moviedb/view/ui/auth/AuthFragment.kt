package com.example.moviedb.view.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviedb.R
import com.example.moviedb.databinding.AuthFragmentBinding
import com.example.moviedb.view.presenter.AuthPresenter
import com.example.moviedb.view.ui.activity.MainActivity
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class AuthFragment : MvpAppCompatFragment(), AuthView {

    private var authBinding: AuthFragmentBinding? = null

    @InjectPresenter
    lateinit var authPresenter: AuthPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = AuthFragmentBinding.inflate(inflater, container, false)
        authBinding = binding
        arguments?.getString("apiKey")?.let { authPresenter.apiKey = it }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        authBinding?.enter?.setOnClickListener {
            authPresenter.createSessionWithLogin(
                authBinding?.loginInput?.text.toString(),
                authBinding?.passwordInput?.text.toString()
            )
        }
    }

    override fun error(message: Int) {
        authBinding?.enter?.apply {
            setBackgroundColor(
                resources.getColor(
                    R.color.orange,
                    activity?.theme
                )
            )
            setTextColor(resources.getColor(R.color.white, activity?.theme))
        }
        authBinding?.errorOutput?.setText(message)
    }

    override fun success(sessionId: String) {
        (activity as MainActivity).presenter.successAuth(sessionId)
    }

    override fun onDestroyView() {
        authBinding = null
        super.onDestroyView()
//        session?.dispose()
//        sessionWithLogin?.dispose()
//        token?.dispose()
    }
}