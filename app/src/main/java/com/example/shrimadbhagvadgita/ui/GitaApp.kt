package com.example.shrimadbhagvadgita.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shrimadbhagvadgita.ViewModel
import com.example.shrimadbhagvadgita.ui.allChapter.HomeScreen
import com.example.shrimadbhagvadgita.ui.singleChapter.ChapterScreen

@Composable
fun Gita() {
    val navController = rememberNavController()
    val viewModel: ViewModel = viewModel(factory = ViewModel.Factory)
    NavHost(navController = navController, startDestination = Screens.MAIN.name) {
        composable(route = Screens.MAIN.name) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.CHAPTER.name+"/{chapter}") {navBackStackEntry ->
            navBackStackEntry.arguments?.let {
                val chapter = it.getString("chapter")?.toInt()
                chapter?.let {
                    ChapterScreen(chapter, viewModel = viewModel, navController = navController)
                }
            }
        }
        composable(route = Screens.SHLOK.name+"/{chapter}/{shlok}") {navBackStackEntry ->
            navBackStackEntry.arguments?.let {
                val chapter = it.getString("chapter")?.toInt()
                val shlok = it.getString("shlok")?.toInt()
                chapter?.let {
                    ShlokScreen(viewModel.getShlok(shlok))
                }
            }
        }
    }
}
enum class Screens {
    MAIN, CHAPTER, SHLOK
}