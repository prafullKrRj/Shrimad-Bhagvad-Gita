package com.example.shrimadbhagvadgita.ui

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GitaViewModel(
    private val bhagvatGitaRepository: BhagvatGitaRepository
): ViewModel() {


    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())

    val uiState = _uiState.asStateFlow()
    suspend fun getShloks(chapter: Int?, totalVerses: Int?){
        _uiState.update {
            it.copy(
                loading = true
            )
        }
        _uiState.update {
            it.copy(
                shloks = bhagvatGitaRepository.getShloks(chapter?:1, totalVerses?:47),
                loading = false
            )
        }
    }
    suspend fun getChapters() {
        _uiState.update {
            it.copy(
                chapters = bhagvatGitaRepository.getChapters()
            )
        }
    }
    suspend fun getSingleChapter(chapter: Int) {
        _uiState.update {
            it.copy(
                singleChapter = bhagvatGitaRepository.getSingleChapter(chapter = chapter)
            )
        }
    }
    fun getShlok(int: Int?): ShlokDto {
        return _uiState.value.shloks[int!!-1]
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
data class UiState (
    val shloks: List<ShlokDto> = listOf(),
    val chapters: Chapters = Chapters(),
    val singleChapter: SingleChapter? = null,
    var loading: Boolean = false
)