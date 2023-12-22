package com.example.shrimadbhagvadgita

import android.app.Application
import com.example.shrimadbhagvadgita.data.AppContainer
import com.example.shrimadbhagvadgita.data.DefaultAppContainer

class BhagvatGitaApplication(): Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}