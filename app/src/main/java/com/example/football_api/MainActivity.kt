package com.example.football_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.football_api.view.FootballTeamsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                FootballTeamsFragment()
            ).commit()
    }
}