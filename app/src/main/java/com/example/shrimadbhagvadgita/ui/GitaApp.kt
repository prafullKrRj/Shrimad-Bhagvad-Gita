package com.example.shrimadbhagvadgita.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shrimadbhagvadgita.ui.allChapter.HomeScreen
import com.example.shrimadbhagvadgita.ui.singleChapter.ChapterScreen

@Composable
fun Gita() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MAIN.name) {
        composable(route = Screens.MAIN.name) {
            HomeScreen(navController = navController)
        }
        composable(route = Screens.CHAPTER.name+"/{chapter}") {navBackStackEntry ->
            navBackStackEntry.arguments?.let {
                val chapter = it.getString("chapter")?.toInt()
                chapter?.let {
                    ChapterScreen(chapter)
                }
            }
        }
        composable(route = Screens.SHLOK.name+"/{shlok}") {navBackStackEntry ->
            navBackStackEntry.arguments?.let {
                val chapter = it.getString("chapter")?.toInt()
                chapter?.let {
                    ChapterScreen(0)
                }
            }
        }
    }
}
enum class Screens {
    MAIN, CHAPTER, SHLOK
}