package com.prafull.shrimadbhagvadgita.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prafull.shrimadbhagvadgita.ui.allChapter.HomeScreen
import com.prafull.shrimadbhagvadgita.ui.shlokScreen.ShlokScreen
import com.prafull.shrimadbhagvadgita.ui.singleChapter.ChapterScreen

@Composable
fun Gita() {
    val navController = rememberNavController()
    val viewModel: GitaViewModel = viewModel(factory = GitaViewModel.Factory)
    NavHost(navController = navController, startDestination = Screens.MAIN.name) {
        composable(route = Screens.MAIN.name) {
            HomeScreen(navController = navController, gitaViewModel = viewModel)
        }
        composable(route = Screens.CHAPTER.name + "/{chapter}") { navBackStackEntry ->
            navBackStackEntry.arguments?.let {
                val chapter = it.getString("chapter")?.toInt()
                chapter?.let {
                    ChapterScreen(chapter, gitaViewModel = viewModel, navController = navController)
                }
            }
        }
        composable(route = Screens.SHLOK.name + "/{chapter}/{shlok}") { navBackStackEntry ->
            navBackStackEntry.arguments?.let {
                val chapter = it.getString("chapter")?.toInt()
                val shlok = it.getString("shlok")?.toInt()
                chapter?.let {
                    ShlokScreen(viewModel.getShlok(shlok), navController)
                }
            }
        }
    }
}

enum class Screens {
    MAIN, CHAPTER, SHLOK
}