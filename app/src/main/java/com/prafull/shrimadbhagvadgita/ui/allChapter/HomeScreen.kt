package com.prafull.shrimadbhagvadgita.ui.allChapter

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.prafull.shrimadbhagvadgita.ui.GitaViewModel
import com.prafull.shrimadbhagvadgita.ui.Screens
import com.prafull.shrimadbhagvadgita.ui.UiState
import com.prafull.shrimadbhagvadgita.ui.allChapter.components.ChapterNameCard

@SuppressLint("MutableCollectionMutableState")
@Composable
fun HomeScreen(navController: NavController, gitaViewModel: GitaViewModel) {
    val uiState: UiState by gitaViewModel.uiState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(context) {
        gitaViewModel.getChapters()
    }
    Scaffold(
        topBar = {
            AppBar(
                label = "Shrimad BhagvadGita"
            )
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Chapters()
            Spacer(modifier = Modifier.height(2.dp))
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), thickness = 2.dp
            )
            Spacer(modifier = Modifier.height(2.dp))
            LazyColumn(
                modifier = Modifier.padding(vertical = 16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                itemsIndexed(uiState.chapters, key = { index, it ->
                    index
                }) { index, chapterModelDto ->
                    ChapterNameCard(index, chapterModelDto) {
                        navController.navigate(route = Screens.CHAPTER.name + "/${index + 1}")
                    }
                }
            }
        }
    }
}

@Composable
private fun Chapters() {
    Text(
        text = "Chapters",
        fontSize = 20.sp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    navIcon: ImageVector? = null,
    navIconClicked: () -> Unit = {},
    actionIcon: ImageVector? = null,
    actionIconClicked: () -> Unit = {},
    labelRow: @Composable () -> Unit = {},
    label: String?
) {
    CenterAlignedTopAppBar(
        title = {
            if (label != null) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            } else {
                labelRow()
            }
        },
        navigationIcon = {
            if (navIcon != null) {
                IconButton(onClick = { navIconClicked() }) {
                    Icon(
                        imageVector = navIcon,
                        contentDescription = null,
                    )
                }
            }
        },
        actions = {
            if (actionIcon != null) {
                IconButton(onClick = { actionIconClicked() }) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = null,
                    )
                }
            }
        }
    )
}