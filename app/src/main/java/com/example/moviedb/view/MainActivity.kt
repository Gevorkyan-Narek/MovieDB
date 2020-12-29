package com.example.moviedb.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.moviedb.R
import com.example.moviedb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AuthFragmentCallback {

    private lateinit var binding: ActivityMainBinding

    private val apiKey = "7a00b045a944e9396f766b8e2b906775"

    private val bundle = Bundle().apply {
        putString("apiKey", apiKey)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movies -> changeFragment(R.id.movies)
                R.id.profile -> changeFragment(R.id.profile)
                R.id.favourite -> changeFragment(R.id.favourite)
                else -> changeFragment(R.id.movies)
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainFragment,
                AuthFragment(this).apply {
                    arguments = bundle
                })
            .commit()
    }

    private fun changeFragment(item: Int): Boolean {
        val fragment = when (item) {
            R.id.movies -> MoviesFragment()
            R.id.profile -> ProfileFragment()
            R.id.favourite -> FavouriteFragment()
            else -> MoviesFragment()
        }

        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainFragment,
                fragment
            ).commit()

        return true
    }

    override fun successAuth(sessionId: String) {
        bundle.putString("sessionId", sessionId)
        binding.navigation.visibility = View.VISIBLE
        changeFragment(R.id.movies)
    }
}