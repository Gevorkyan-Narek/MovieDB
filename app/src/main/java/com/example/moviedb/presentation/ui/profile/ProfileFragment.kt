package com.example.moviedb.presentation.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviedb.R
import com.example.moviedb.databinding.ProfileFragmentBinding
import com.example.moviedb.presentation.presenters.ProfilePresenter
import com.example.moviedb.presentation.ui.activity.MainActivity
import com.example.moviedb.setAvatar
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
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        profilePresenter.openProfile()

        profileBinding?.exit?.setOnClickListener {
            profilePresenter.exitProfile()
        }
    }


    override fun onDestroyView() {
        profileBinding = null
        super.onDestroyView()
    }

    override fun setName(name: String) {
        profileBinding?.name?.text = name
    }

    override fun setAvatar(avatar: String?) {
        if (avatar == null)
            profileBinding?.avatar?.setAvatar(R.drawable.avatar)
        else
            profileBinding?.avatar?.setAvatar("https://image.tmdb.org/t/p/w500$avatar")
    }

    override fun exitProfile() {
        (activity as MainActivity).presenter.exitAuth()
    }
}