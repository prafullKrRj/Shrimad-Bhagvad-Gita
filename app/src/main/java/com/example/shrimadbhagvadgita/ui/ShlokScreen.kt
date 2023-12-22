package com.example.shrimadbhagvadgita.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shrimadbhagvadgita.model.shlokDto.ShlokDto

@Composable
fun ShlokScreen(shlokDto: ShlokDto) {
    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(vertical = 8.dp) // 8.dp between each item
    ) {
        item {
            Text(text = shlokDto.slok)
        }
        items(items = listOf<Any>()) { item ->

        }
    }
}