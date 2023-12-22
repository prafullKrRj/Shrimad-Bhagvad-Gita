package com.example.shrimadbhagvadgita.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shrimadbhagvadgita.model.shlokDto.ShlokDto
import com.example.shrimadbhagvadgita.ui.allChapter.AppBar

@Composable
fun ShlokScreen(shlokDto: ShlokDto) {
    Scaffold(
        topBar = { AppBar(title = "Verse: ${shlokDto.chapter}: ${shlokDto.verse}")}
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(horizontal = 12.dp),
            contentPadding = padding
        ) {
            item {
                Text(text = shlokDto.slok)
            }

        }
    }
    
}

@Composable
fun AllTranslations() {

}