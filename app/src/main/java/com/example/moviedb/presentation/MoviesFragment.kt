package com.example.moviedb.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviedb.R
import com.example.moviedb.databinding.MoviesFragmentBinding

class MoviesFragment : Fragment(R.layout.movies_fragment) {

    lateinit var moviesBinding: MoviesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MoviesFragmentBinding.inflate(inflater, container, false)
        moviesBinding = binding
        return binding.root
    }
}