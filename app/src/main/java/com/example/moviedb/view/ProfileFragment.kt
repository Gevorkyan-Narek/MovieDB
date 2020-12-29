package com.example.moviedb.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.databinding.ProfileFragmentBinding

class ProfileFragment: Fragment() {

    private var profileBinding: ProfileFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ProfileFragmentBinding.inflate(inflater, container, false)
        profileBinding = binding

        Glide.with(this)
            .load(R.drawable.avatar)
            .circleCrop()
            .into(binding.avatar)



        return binding.root
    }

    override fun onDestroyView() {
        profileBinding = null
        super.onDestroyView()
    }
}