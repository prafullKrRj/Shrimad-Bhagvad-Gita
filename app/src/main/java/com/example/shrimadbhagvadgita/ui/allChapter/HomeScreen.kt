package com.example.shrimadbhagvadgita.ui.allChapter

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shrimadbhagvadgita.ViewModel
import com.example.shrimadbhagvadgita.model.ChaptersCombined.ChaptersModelDto
import com.example.shrimadbhagvadgita.ui.Screens

@SuppressLint("MutableCollectionMutableState")
@Composable
fun HomeScreen(navController: NavController, viewModel: ViewModel) {
    val chapters = viewModel.chapters.value
    val context = LocalContext.current
    LaunchedEffect(context) {
        viewModel.getChapters()
    }
    Scaffold(
        topBar = {
            AppBar("Shrimad BhagvadGita")
        }
    ) { padding ->
        LazyColumn(contentPadding = padding, modifier = Modifier.padding(16.dp)) {
            chapters.forEachIndexed { index, chapterModelDto ->
                item {
                    VerseCard(index, chapterModelDto) {
                        navController.navigate(route = Screens.CHAPTER.name+"/${index+1}")
                    }
                }
            }
        }
    }
}

@Composable
fun VerseCard(index: Int, chapterModelDto: ChaptersModelDto, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 70.dp)
            .padding(vertical = 6.dp)
            //.background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable {
                onClick()
            }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "${index+1}: ",
                modifier = Modifier.padding(vertical = 8.dp).padding(start = 12.dp),
                fontSize = 16.sp
            )
            Column(
                modifier = Modifier.padding(vertical = 8.dp).padding(end = 12.dp).fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${chapterModelDto.name} - ${chapterModelDto.translation}",
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    )
}
