package com.prafull.shrimadbhagvadgita

import android.app.Application
import com.prafull.shrimadbhagvadgita.data.AppContainer
import com.prafull.shrimadbhagvadgita.data.DefaultAppContainer

class BhagvatGitaApplication() : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}