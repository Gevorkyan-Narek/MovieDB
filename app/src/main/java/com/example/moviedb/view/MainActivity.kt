package com.example.moviedb.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviedb.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragment, AuthFragment())
            .commit()
    }
}