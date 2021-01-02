package com.example.moviedb.view.ui.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.moviedb.R
import com.example.moviedb.databinding.ActivityMainBinding
import com.example.moviedb.view.presenter.ActivityPresenter
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
        binding.navigation.selectedItemId = R.id.movies
    }

    override fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainFragment,
                fragment
            ).commit()
    }

    override fun isVisibleBottomNavigation() {
        binding.navigation.visibility =
            if (binding.navigation.visibility == View.GONE)
                View.VISIBLE
            else
                View.GONE
    }
}