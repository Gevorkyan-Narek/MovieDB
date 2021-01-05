package com.example.moviedb.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.moviedb.R
import com.example.moviedb.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter


class MainActivity : MvpAppCompatActivity(), ActivityView {

    private lateinit var binding: ActivityMainBinding

    @InjectPresenter
    lateinit var presenter: ActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.movies -> presenter.displayMovies()
                R.id.profile -> presenter.displayProfile()
                R.id.favourite -> presenter.displayFavourite()
                else -> presenter.displayMovies()
            }
            true
        }
    }

    override fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainFragment,
                fragment
            ).commit()
    }

    override fun successAuth() {
        binding.navigation.selectedItemId = R.id.movies
    }

    override fun visibleBottomNavigation() {
        binding.navigation.visibility = View.VISIBLE
    }

    override fun goneBottomNavigation() {
        binding.navigation.visibility = View.GONE
    }
}