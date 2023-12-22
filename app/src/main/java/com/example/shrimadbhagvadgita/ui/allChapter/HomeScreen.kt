package com.example.shrimadbhagvadgita.ui.allChapter

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shrimadbhagvadgita.model.ChaptersCombined.Chapters
import com.example.shrimadbhagvadgita.model.ChaptersCombined.ChaptersModelDto
import com.example.shrimadbhagvadgita.ui.Screens
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen(navController: NavController) {
    var chapters by rememberSaveable {
        mutableStateOf(Chapters())
    }
    val context = LocalContext.current
    LaunchedEffect(context) {
        val data = readJsonData(context, "chapters/chapters.json")
        chapters = Gson().fromJson(data, Chapters::class.java)
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
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp).clickable {
                onClick()
            }
    ) {
        Row {
            Text(
                text = "${index+1}: ",
                modifier = Modifier.padding(vertical = 8.dp).padding(start = 12.dp),
                fontSize = 16.sp
            )
            Column(
                modifier = Modifier.padding(vertical = 8.dp).padding(end = 12.dp)
            ) {
                Text(
                    text = "${chapterModelDto.name} - ${chapterModelDto.translation}",
                    fontSize = 16.sp,
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
suspend fun readJsonData(context: Context, fileName: String): String =
    withContext(Dispatchers.IO) {
        val inputStream = context.assets.open(fileName)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        inputStream.close()
        jsonString
}