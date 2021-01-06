package com.example.moviedb.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviedb.R
import com.example.moviedb.data.models.Account
import com.example.moviedb.databinding.ProfileFragmentBinding
import com.example.moviedb.domain.setAvatar
import com.example.moviedb.presentation.activity.MainActivity
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

        profileBinding?.exit?.setOnClickListener {
            profilePresenter.exitProfile()
        }
    }

    override fun onDestroyView() {
        profileBinding = null
        super.onDestroyView()
    }

    override fun exitProfile() = (activity as MainActivity).presenter.exitAuth()

    override fun getProfile(account: Account) {
        profileBinding?.name?.text =
            if (account.name.isNotEmpty())
                account.name
            else
                account.username

        val avatar = account.avatar.tmdb.avatarPath

        if (avatar.isNullOrEmpty())
            profileBinding?.avatar?.setAvatar(R.drawable.avatar)
        else
            profileBinding?.avatar?.setAvatar(avatar)
    }

    override fun visibleLoading() {
        profileBinding?.progressBar?.visibility = View.VISIBLE
    }

    override fun goneLoading() {
        profileBinding?.progressBar?.visibility = View.GONE
    }
}