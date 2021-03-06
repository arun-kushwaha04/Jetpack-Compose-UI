package com.jetpack.meditationui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.jetpack.meditationui.ui.HomeScreen
import com.jetpack.meditationui.ui.theme.MeditationUI

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationUI {
                HomeScreen()
            }
        }
    }
}
