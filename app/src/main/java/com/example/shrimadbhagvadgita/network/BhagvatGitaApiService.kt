package com.example.shrimadbhagvadgita.network

import com.example.shrimadbhagvadgita.model.shlokDto.ShlokDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BhagvatGitaApiService {
    @GET("/slok/{shlok}")
    suspend fun getShlokDetail(
        @Path ("shlok") shlok: String
    ): ShlokDto
}