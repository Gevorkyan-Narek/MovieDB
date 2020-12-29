package com.example.moviedb.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.databinding.ProfileFragmentBinding
import com.example.moviedb.network.TheMovieNetworkConnect
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class ProfileFragment : Fragment() {

    private var profileBinding: ProfileFragmentBinding? = null
    private val retrofit = TheMovieNetworkConnect.getAccount()
    private var accountDisp: Disposable? = null

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

                Log.d("Api", apiKey)
                Log.d("Session", sessionId)
                getAccount(apiKey, sessionId)
            }
        }


        Glide.with(this)
            .load(R.drawable.avatar)
            .circleCrop()
            .into(binding.avatar)

        return binding.root
    }

    private fun getAccount(apiKey: String, sessionId: String) {
        accountDisp = retrofit.getAccountId(apiKey, sessionId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                profileBinding?.let { profile ->
                    profile.name.text = if (it.name.isNotEmpty()) it.name else it.username
                    if (it.avatar.tmdb.avatarPath.isNotEmpty())
                        it.avatar.tmdb.avatarPath


                    Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w500${it.avatar.tmdb.avatarPath}")
                        .circleCrop()
                        .into(profile.avatar)
                }

            }, {
                Log.d("Error", it.message ?: "null")
            })
    }

    override fun onDestroyView() {
        profileBinding = null
        super.onDestroyView()
        accountDisp?.dispose()
    }
}