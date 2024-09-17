package com.prafull.shrimadbhagvadgita.ui.singleChapter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prafull.shrimadbhagvadgita.model.SingleChapter.SingleChapter

@Composable
fun ChapterScreenUi(modifier: Modifier, chapter: SingleChapter, slideToNextPage: () -> Unit) {
    LazyColumn(
            modifier = modifier.padding(bottom = 12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        item {
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
            ) {
                FilledIconButton(
                        onClick = {
                            slideToNextPage()
                        }
                ) {
                    Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Slide"
                    )
                }
            }
        }
        item {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(text = "${chapter.chapter_number}: ${chapter.name}", fontSize = 22.sp)
                Text(text = "\t\t\t/:" + chapter.transliteration, fontSize = 16.sp)
            }
        }
        item {
            MeaningCard(chapter = chapter)
        }
        item {
            SummaryCard(chapter = chapter)
        }
    }
}

@Composable
fun SummaryCard(chapter: SingleChapter) {
    OutlinedCard {
        Text(
                text = "Summary",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
        )
        if (chapter.summary.en.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            InternalSummaryCard(language = "English", content = chapter.summary.en)
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (chapter.summary.hi.isNotBlank()) {
            InternalSummaryCard(language = "Hindi", content = chapter.summary.hi)
        }
    }
}

@Composable
private fun InternalSummaryCard(language: String, content: String) {
    Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
        ) {
            Text(
                    text = language,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic
            )
            Text(text = content, fontSize = 16.sp)
        }
    }
}

@Composable
private fun MeaningCard(chapter: SingleChapter) {
    Divider(Modifier.fillMaxWidth())
    OutlinedCard(
            modifier = Modifier.padding(vertical = 12.dp),
            //  colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
    ) {
        Text(
                text = "Meaning",
                Modifier.padding(10.dp),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
        )
        Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                shape = RectangleShape
        ) {
            Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
            ) {
                Text(text = "English", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Medium)
                Text(text = chapter.meaning.en)
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = "Hindi", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Medium)
                Text(text = chapter.meaning.hi)
            }
        }
    }
}