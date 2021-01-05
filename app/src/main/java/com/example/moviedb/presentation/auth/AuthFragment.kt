package com.example.moviedb.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviedb.R
import com.example.moviedb.databinding.AuthFragmentBinding
import com.example.moviedb.presentation.activity.MainActivity
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
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        authBinding?.enter?.setOnClickListener {
            authBinding?.progressBar?.visibility = View.VISIBLE
            authPresenter.authorize(
                authBinding?.loginInput?.text.toString(),
                authBinding?.passwordInput?.text.toString()
            )
        }
    }

    override fun fail(message: Int) {
        authBinding?.progressBar?.visibility = View.INVISIBLE
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

    override fun success() {
        authBinding?.progressBar?.visibility = View.INVISIBLE
        (activity as MainActivity).presenter.successAuth()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        authBinding = null
    }
}