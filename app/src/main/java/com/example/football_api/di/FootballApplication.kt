package com.example.football_api.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FootballApplication :Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FootballApplication)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}