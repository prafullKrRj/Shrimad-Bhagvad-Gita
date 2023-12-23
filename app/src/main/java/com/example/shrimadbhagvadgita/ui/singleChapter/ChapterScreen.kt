package com.example.shrimadbhagvadgita.ui.singleChapter

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.shrimadbhagvadgita.ui.GitaViewModel
import com.example.shrimadbhagvadgita.ui.Screens
import com.example.shrimadbhagvadgita.ui.allChapter.AppBar
import com.example.shrimadbhagvadgita.ui.singleChapter.components.ChapterScreenUi
import com.example.shrimadbhagvadgita.ui.singleChapter.components.ShloksView
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChapterScreen(chapterNumber: Int, gitaViewModel: GitaViewModel, navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val chapterDetail = gitaViewModel.singleChapter.value
    val shloks = gitaViewModel.shloks.value
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
                label = chapterDetail?.name,
                navIconClicked = {
                    navController.popBackStack()
                },
                navIcon = Icons.Default.ArrowBack
            )
        }
    ) { paddingValues ->
        if (chapterDetail != null) {
            Column(modifier = Modifier.fillMaxSize()) {
                HorizontalPager(state = pagerState, userScrollEnabled = true) {
                    when (it) {
                        0 -> {
                            ChapterScreenUi(modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues), chapter = chapterDetail
                            ) {
                                scope.launch {
                                    pagerState.animateScrollToPage(1)
                                }
                            }
                        }
                        1 -> {
                            if (shloks.isEmpty()) {
                                LaunchedEffect(Unit) {
                                    gitaViewModel.getShloks(chapterNumber, chapterDetail.verses_count)
                                }
                            }
                            ShloksView(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(paddingValues),
                                shloks = shloks,
                                navigateToShlok = { shlok ->
                                    navController.navigate(Screens.SHLOK.name+"/$chapterNumber/$shlok")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}