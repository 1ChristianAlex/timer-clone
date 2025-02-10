package com.timerclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.timerclone.ui.TimerCloneApp
import com.timerclone.ui.screen.common.appbar.TimerCloneBottomBar
import com.timerclone.ui.theme.TimerCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimerCloneTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(), bottomBar = { TimerCloneBottomBar() }
                ) { innerPadding ->
                    Box(
                        Modifier
                            .padding(innerPadding)
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        TimerCloneApp()
                    }
                }
            }
        }
    }
}

