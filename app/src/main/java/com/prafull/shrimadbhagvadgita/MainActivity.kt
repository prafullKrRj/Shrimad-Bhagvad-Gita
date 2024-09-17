package com.prafull.shrimadbhagvadgita

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.prafull.shrimadbhagvadgita.ui.Gita
import com.prafull.shrimadbhagvadgita.ui.theme.ShrimadBhagvadGitaTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("MutableCollectionMutableState")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShrimadBhagvadGitaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Gita()
                }
            }
        }
    }
}