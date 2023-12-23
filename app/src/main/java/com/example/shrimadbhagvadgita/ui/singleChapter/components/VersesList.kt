package com.example.shrimadbhagvadgita.ui.singleChapter.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shrimadbhagvadgita.model.shlokDto.ShlokDto

@Composable
fun ShloksView(modifier: Modifier, shloks: List<ShlokDto>, navigateToShlok: (Int) -> Unit) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(horizontal = 12.dp)) {
        itemsIndexed(shloks) { idx, shlok ->

            ListItemComposable(modifier = Modifier.padding(vertical = 8.dp), shlok = shlok, idx = idx) {
                navigateToShlok(idx+1)
            }
        }
    }
}
@Composable
fun ListItemComposable(modifier: Modifier, shlok: ShlokDto, idx: Int, navigateToShlok: () -> Unit) {
    OutlinedCard (
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                navigateToShlok()
            },
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
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