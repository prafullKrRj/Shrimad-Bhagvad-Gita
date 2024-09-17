package com.prafull.shrimadbhagvadgita.ui.singleChapter

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.prafull.shrimadbhagvadgita.ui.GitaViewModel
import com.prafull.shrimadbhagvadgita.ui.Screens
import com.prafull.shrimadbhagvadgita.ui.allChapter.AppBar
import com.prafull.shrimadbhagvadgita.ui.singleChapter.components.ChapterScreenUi
import com.prafull.shrimadbhagvadgita.ui.singleChapter.components.ShloksView
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChapterScreen(
    chapterNumber: Int,
    gitaViewModel: GitaViewModel,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val uiState by gitaViewModel.uiState.collectAsState()
    val context = LocalContext.current
    val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f
    ) {
        2
    }
    LaunchedEffect(context) {
        gitaViewModel.getSingleChapter(chapterNumber)
    }
    Scaffold(
            topBar = {
                AppBar(
                        label = uiState.singleChapter?.name,
                        navIconClicked = {
                            navController.popBackStack()
                        },
                        navIcon = Icons.Default.ArrowBack
                )
            }
    ) { paddingValues ->
        if (uiState.singleChapter != null) {
            Column(modifier = Modifier.fillMaxSize()) {
                HorizontalPager(state = pagerState, userScrollEnabled = true) {
                    when (it) {
                        0 -> {
                            ChapterScreenUi(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(paddingValues), chapter = uiState.singleChapter!!
                            ) {
                                scope.launch {
                                    pagerState.animateScrollToPage(1)
                                }
                            }
                        }

                        1 -> {
                            LaunchedEffect(Unit) {
                                gitaViewModel.getShloks(
                                        chapterNumber,
                                        uiState.singleChapter!!.verses_count
                                )
                            }
                            if (uiState.loading) {
                                Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            } else {
                                ShloksView(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(paddingValues),
                                        shloks = uiState.shloks,
                                        navigateToShlok = { shlok ->
                                            navController.navigate(Screens.SHLOK.name + "/$chapterNumber/$shlok")
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}