package com.example.shrimadbhagvadgita

import android.app.Application
import android.health.connect.datatypes.units.Length
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.shrimadbhagvadgita.data.BhagvatGitaRepository
import com.example.shrimadbhagvadgita.model.shlokDto.ShlokDto
import kotlinx.coroutines.launch

class ViewModel(
    private val bhagvatGitaRepository: BhagvatGitaRepository
): ViewModel() {

    var shlokState: ShlokState by mutableStateOf(ShlokState.Loading)
        private set

    fun getShloks(chapter: Int, numbers: Int) {
        viewModelScope.launch {
            shlokState = try {
                val list: MutableList<ShlokDto> = mutableListOf()
                for (i in 1..numbers) {

                    list.add(bhagvatGitaRepository.getShlok(chapter, i))
                }
                ShlokState.Success(list)
            } catch (e: Exception) {
                ShlokState.Error(error = e.message.toString())
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BhagvatGitaApplication)
                val bhagvatGitaRepository = application.container.bhagvatGitaRepository
                ViewModel(bhagvatGitaRepository = bhagvatGitaRepository)
            }
        }
    }
}
sealed class ShlokState {
    object Loading : ShlokState()
    data class Success(val data: List<ShlokDto>) : ShlokState()
    data class Error(val error: String) : ShlokState()
}