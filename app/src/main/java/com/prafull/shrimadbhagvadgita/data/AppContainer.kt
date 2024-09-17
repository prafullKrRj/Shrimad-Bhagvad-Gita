package com.prafull.shrimadbhagvadgita.data

import android.content.Context

interface AppContainer {
    val bhagvatGitaRepository: BhagvatGitaRepository
}

class DefaultAppContainer(context: Context) : AppContainer {
    override val bhagvatGitaRepository: BhagvatGitaRepository by lazy {
        BhagvatGitaRepositoryImpl(context)
    }
}