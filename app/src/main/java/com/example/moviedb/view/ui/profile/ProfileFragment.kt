package com.example.moviedb.view.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviedb.R
import com.example.moviedb.databinding.ProfileFragmentBinding
import com.example.moviedb.setAvatar
import com.example.moviedb.view.presenter.ProfilePresenter
import com.example.moviedb.view.ui.activity.MainActivity
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    private var profileBinding: ProfileFragmentBinding? = null

    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ProfileFragmentBinding.inflate(inflater, container, false)
        profileBinding = binding

        arguments?.let {
            val apiKey = it.getString("apiKey")
            val sessionId = it.getString("sessionId")

            if (apiKey != null && sessionId != null) {
                profilePresenter.apiKey = apiKey
                profilePresenter.sessionId = sessionId
                profilePresenter.getAccount()
            }
        }

        binding.exit.setOnClickListener {
            profilePresenter.exitProfile()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        profileBinding?.avatar?.setAvatar(R.drawable.avatar)
    }


    override fun onDestroyView() {
        profileBinding = null
        super.onDestroyView()
    }

    override fun setName(name: String) {
        profileBinding?.name?.text = name
    }

    override fun setAvatar(avatar: String?) {
        profileBinding?.avatar?.setAvatar("https://image.tmdb.org/t/p/w500$avatar")
    }

    override fun exitProfile() {
        (activity as MainActivity).presenter.exitAuth()
    }
}