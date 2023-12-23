package com.example.shrimadbhagvadgita.ui.allChapter.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shrimadbhagvadgita.model.ChaptersCombined.ChaptersModelDto

@Composable
fun ChapterNameCard(index: Int, chapterModelDto: ChaptersModelDto, onClick: () -> Unit) {
    OutlinedCard(
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 70.dp)
            .padding(vertical = 6.dp)
            .clickable {
                onClick()
            },
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "${index+1}: ",
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(start = 12.dp),
                fontSize = 16.sp
            )
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(end = 12.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${chapterModelDto.name} - ${chapterModelDto.translation}",
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Meaning: ${chapterModelDto.meaning.en}", fontStyle = FontStyle.Italic)
            }
        }
    }
}