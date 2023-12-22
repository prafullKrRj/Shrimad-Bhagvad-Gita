package com.example.shrimadbhagvadgita.ui.singleChapter

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shrimadbhagvadgita.ShlokState
import com.example.shrimadbhagvadgita.ViewModel
import com.example.shrimadbhagvadgita.model.SingleChapter.SingleChapter
import com.example.shrimadbhagvadgita.ui.allChapter.AppBar
import com.example.shrimadbhagvadgita.ui.allChapter.readJsonData
import com.google.gson.Gson

@Composable
fun ChapterScreen(index: Int) {
    val viewModel: ViewModel = viewModel(factory = ViewModel.Factory)
    var showVerses by rememberSaveable {
        mutableStateOf(false)
    }
    val state = viewModel.shlokState
    var chapter by rememberSaveable {
        mutableStateOf<SingleChapter?>(null)
    }
    val context = LocalContext.current
    LaunchedEffect(context) {
        val data = readJsonData(context, "chapter/$index/index.json")
        chapter = Gson().fromJson(data, SingleChapter::class.java)
    }
    Scaffold(
        topBar = { AppBar("${chapter?.name}") }
    ) { paddingValues ->
        if (chapter != null) {
            Column {
                ChapterScreenUi(modifier = Modifier.padding(paddingValues), chapter = chapter!!) {
                    Toast.makeText(context,  "working", Toast.LENGTH_SHORT).show()
                    viewModel.getShloks(chapter = index, numbers = chapter!!.verses_count)
                    if (!showVerses) showVerses = true
                }
                if (showVerses) {
                    when (state) {
                        is ShlokState.Error -> {
                            Text(text = "Error")
                        }
                        is ShlokState.Loading -> {
                            CircularProgressIndicator()
                        }
                        is ShlokState.Success -> {
                            Text(text = state.data[0].slok)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChapterScreenUi(modifier: Modifier, chapter: SingleChapter, showVerses: () -> Unit) {
    Column(
        modifier = modifier,
    ) {
        Text(text = "${chapter.chapter_number}+${chapter.name}")
        if (chapter.summary.en.isNotBlank()) {
            Text(text = chapter.summary.en)
        }
        if (chapter.summary.hi.isNotBlank()) {
            Text(text = chapter.summary.hi)
        }
        if (chapter.translation.isNotBlank()) {
            Text(text = chapter.translation)
        }
        Button(
            onClick = {
                showVerses()
            }
        ) {
            Text("See verses")
        }
    }
}
