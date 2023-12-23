package com.example.shrimadbhagvadgita.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

    val shloks: MutableState<List<ShlokDto>> = mutableStateOf(listOf())
    @SuppressLint("MutableCollectionMutableState")
    val chapters: MutableState<Chapters> = mutableStateOf(Chapters())
    val singleChapter: MutableState<SingleChapter?> = mutableStateOf(null)
    suspend fun getShloks(chapter: Int, totalVerses: Int){
        shloks.value = bhagvatGitaRepository.getShloks(chapter, totalVerses)
    }
    suspend fun getChapters() {
        chapters.value =  bhagvatGitaRepository.getChapters()
    }
    suspend fun getSingleChapter(chapter: Int) {
        singleChapter.value =  bhagvatGitaRepository.getSingleChapter(chapter = chapter)
    }
    fun getShlok(int: Int?): ShlokDto {
        return shloks.value[int!!-1]
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