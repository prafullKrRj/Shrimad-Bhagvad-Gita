package com.example.shrimadbhagvadgita.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.shrimadbhagvadgita.BhagvatGitaApplication
import com.example.shrimadbhagvadgita.data.BhagvatGitaRepository
import com.example.shrimadbhagvadgita.model.ChaptersCombined.Chapters
import com.example.shrimadbhagvadgita.model.SingleChapter.SingleChapter
import com.example.shrimadbhagvadgita.model.shlokDto.ShlokDto

class GitaViewModel(
    private val bhagvatGitaRepository: BhagvatGitaRepository
): ViewModel() {

    var shloks: List<ShlokDto> by mutableStateOf(
        mutableListOf()
    )
    var chapters: Chapters by mutableStateOf(Chapters())
    var singleChapter: SingleChapter? by mutableStateOf(null)
    suspend fun getShloks(chapter: Int, totalVerses: Int){
        shloks = bhagvatGitaRepository.getShloks(chapter, totalVerses)
    }
    suspend fun getChapters() {
        chapters =  bhagvatGitaRepository.getChapters()
    }
    suspend fun getSingleChapter(chapter: Int) {
        singleChapter =  bhagvatGitaRepository.getSingleChapter(chapter = chapter)
    }
    fun getShlok(int: Int?): ShlokDto {
        return shloks[int!!-1]
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BhagvatGitaApplication)
                val bhagvatGitaRepository = application.container.bhagvatGitaRepository
                GitaViewModel(bhagvatGitaRepository = bhagvatGitaRepository)
            }
        }
    }
}