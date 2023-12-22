package com.example.shrimadbhagvadgita.data

import com.example.shrimadbhagvadgita.model.shlokDto.ShlokDto
import com.example.shrimadbhagvadgita.network.BhagvatGitaApiService

interface BhagvatGitaRepository {
    suspend fun getShlok(chapter: Int, shlok: Int): ShlokDto
}

class BhagvatGitaRepositoryImpl (
    private val apiService: BhagvatGitaApiService
): BhagvatGitaRepository {
    override suspend fun getShlok(chapter: Int, shlok: Int): ShlokDto {
        return apiService.getShlokDetail("$chapter/$shlok")
    }
}