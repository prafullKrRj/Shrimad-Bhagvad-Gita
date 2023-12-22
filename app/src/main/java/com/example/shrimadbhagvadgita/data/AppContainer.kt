package com.example.shrimadbhagvadgita.data

import com.example.shrimadbhagvadgita.network.BhagvatGitaApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val bhagvatGitaRepository: BhagvatGitaRepository
}
class DefaultAppContainer: AppContainer {
    private val baseUrl =
        "https://bhagavadgitaapi.in/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: BhagvatGitaApiService by lazy {
        retrofit.create(BhagvatGitaApiService::class.java)
    }

    override val bhagvatGitaRepository: BhagvatGitaRepository by lazy {
        BhagvatGitaRepositoryImpl(apiService = retrofitService)
    }
}