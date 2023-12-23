package com.example.shrimadbhagvadgita.ui.shlokScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shrimadbhagvadgita.R
import com.example.shrimadbhagvadgita.model.shlokDto.AuthorDetails
import com.example.shrimadbhagvadgita.model.shlokDto.ShlokDto
import com.example.shrimadbhagvadgita.ui.allChapter.AppBar

@Composable
fun ShlokScreen(shlokDto: ShlokDto, navController: NavHostController) {
    Scaffold(
        topBar = {
            AppBar(
                label = "Verse: ${shlokDto.chapter}: ${shlokDto.verse}",
                navIconClicked = {
                    navController.popBackStack()
                },
                navIcon = Icons.Default.ArrowBack
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(horizontal = 12.dp),
            contentPadding = padding
        ) {
            item {
                Card {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = shlokDto.slok, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = shlokDto.transliteration, textAlign = TextAlign.Center)
                    }
                }
            }
            item {
                Authors(shlok = shlokDto)
            }
        }
    }
}

@Composable
fun Authors(shlok: ShlokDto) {
    AuthorCard(authorDetails = shlok.adi.toAuthorDetails())
    AuthorCard(authorDetails = shlok.chinmay.toAuthorDetails())
    AuthorCard(authorDetails = shlok.sankar.toAuthorDetails())
    AuthorCard(authorDetails = shlok.abhinav.toAuthorDetails())
    AuthorCard(authorDetails = shlok.jaya.toAuthorDetails())
    AuthorCard(authorDetails = shlok.anand.toAuthorDetails())
    AuthorCard(authorDetails = shlok.dhan.toAuthorDetails())
    AuthorCard(authorDetails = shlok.gambir.toAuthorDetails())
    AuthorCard(authorDetails = shlok.madhav.toAuthorDetails())
    AuthorCard(authorDetails = shlok.ms.toAuthorDetails(),)
    AuthorCard(authorDetails = shlok.neel.toAuthorDetails())
    AuthorCard(authorDetails = shlok.purohit.toAuthorDetails())
    AuthorCard(authorDetails = shlok.puru.toAuthorDetails())
    AuthorCard(authorDetails = shlok.raman.toAuthorDetails())
    AuthorCard(authorDetails = shlok.rams.toAuthorDetails())
    AuthorCard(authorDetails = shlok.san.toAuthorDetails())

    AuthorCard(authorDetails = shlok.siva.toAuthorDetails())
    AuthorCard(authorDetails = shlok.srid.toAuthorDetails())
    AuthorCard(authorDetails = shlok.tej.toAuthorDetails())
    AuthorCard(authorDetails = shlok.vallabh.toAuthorDetails())
    AuthorCard(authorDetails = shlok.venkat.toAuthorDetails())
}
@Composable
fun AuthorCard(
    authorDetails: AuthorDetails,
) {
    var expandCard by rememberSaveable {
        mutableStateOf(false)
    }
    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = authorDetails.author, modifier = Modifier.padding(16.dp))
                IconButton(
                    onClick = {
                        if (checkNull(authorDetails)) {
                            expandCard = !expandCard
                        }
                    }
                ) {
                    Icon(
                        painter = if (expandCard) {
                            painterResource(id = R.drawable.up)
                        } else {
                            painterResource(id = R.drawable.down)
                        },
                        contentDescription = "Expand"
                    )
                }
            }
            if (expandCard) {
                if (!authorDetails.sc.isNullOrEmpty()) {
                    InternalSummaryCard(language = "sc", content = authorDetails.sc)
                }
                if (!authorDetails.et.isNullOrEmpty()) {
                    InternalSummaryCard(language = "et", content = authorDetails.et)
                }
                if (!authorDetails.hc.isNullOrEmpty()) {
                    InternalSummaryCard(language = "hc", content = authorDetails.hc)
                }
                if (!authorDetails.ht.isNullOrEmpty()) {
                    InternalSummaryCard(language = "ht", content = authorDetails.ht)
                }
                if (!authorDetails.ec.isNullOrEmpty()) {
                    InternalSummaryCard(language = "ec", content = authorDetails.ec)
                }
            }
        }
    }
}
private fun checkNull(authorDetails: AuthorDetails): Boolean {
    return  !authorDetails.et.isNullOrEmpty() ||
            !authorDetails.sc.isNullOrBlank() ||
            !authorDetails.ec.isNullOrBlank() ||
            !authorDetails.hc.isNullOrBlank() ||
            !authorDetails.ht.isNullOrBlank()
}
@Composable
private fun InternalSummaryCard(language: String, content: String) {
    var seeMore by rememberSaveable {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = language, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Italic)
            Text(
                text = if (content.length > 300 && !seeMore) {
                    content.substring(0, 300)+"..."
                } else {
                    content
                }, fontSize = 16.sp
            )
            if (content.length > 300) {
                TextButton(onClick = { seeMore = !seeMore }, modifier = Modifier.align(Alignment.End)) {
                    Text(
                        text = if (seeMore) "See Less" else "See More",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}