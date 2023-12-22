package com.example.shrimadbhagvadgita.ui.singleChapter

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shrimadbhagvadgita.ViewModel
import com.example.shrimadbhagvadgita.model.SingleChapter.SingleChapter
import com.example.shrimadbhagvadgita.model.shlokDto.ShlokDto
import com.example.shrimadbhagvadgita.ui.Screens
import com.example.shrimadbhagvadgita.ui.allChapter.AppBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChapterScreen(chapterNumber: Int, viewModel: ViewModel, navController: NavHostController) {
    val chapterDetail = viewModel.singleChapter.value
    val shloks = viewModel.shloks.value
    val context = LocalContext.current
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        2
    }
    LaunchedEffect(context) {
        viewModel.getSingleChapter(chapterNumber)
    }
    Scaffold(
        modifier = Modifier.padding(horizontal = 12.dp),
        topBar = { AppBar("${chapterDetail?.name} - ${chapterDetail?.translation}") }
    ) { paddingValues ->
        if (chapterDetail != null) {
            Column(modifier = Modifier.fillMaxSize()) {
                HorizontalPager(state = pagerState, userScrollEnabled = true) {
                    when (it) {
                        0 -> {
                            ChapterScreenUi(modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues), chapter = chapterDetail!!)
                        }
                        1 -> {
                            if (shloks.isEmpty()) {
                                LaunchedEffect(Unit) {
                                    viewModel.getShloks(chapterNumber, chapterDetail!!.verses_count)
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

@Composable
fun ChapterScreenUi(modifier: Modifier, chapter: SingleChapter) {
    Column(
        modifier = modifier,
    ) {
        Text(text = "${chapter.chapter_number} ${chapter.name}")
        if (chapter.summary.en.isNotBlank()) {
            Text(text = chapter.summary.en)
        }
        if (chapter.summary.hi.isNotBlank()) {
            Text(text = chapter.summary.hi)
        }
        if (chapter.translation.isNotBlank()) {
            Text(text = chapter.translation)
        }
    }
}

@Composable
fun ShloksView(modifier: Modifier, shloks: List<ShlokDto>, navigateToShlok: (Int) -> Unit) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(shloks) {
                idx, shlok ->
            ListItemComposable(modifier = Modifier.padding(vertical = 8.dp), shlok = shlok, idx = idx) {
                navigateToShlok(idx+1)
            }
        }
    }
}
@Composable
fun ListItemComposable(modifier: Modifier, shlok: ShlokDto, idx: Int, navigateToShlok: () -> Unit) {
    Card (
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
            .clickable {
                navigateToShlok()
            }
    ) {
        Column (
            Modifier
                .padding(start = 16.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Row (
                    Modifier.padding(end = 4.dp), verticalAlignment = Alignment.CenterVertically){
                    Text(text = "${idx+1}.")
                    Spacer(modifier = Modifier.width(6.dp))
                    Column (verticalArrangement = Arrangement.Center){
                        Text(text = shlok.slok, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}