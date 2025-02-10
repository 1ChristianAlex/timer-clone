package com.timerclone.ui.screen.common.appbar

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun TimerCloneBottomBar() {
    BottomAppBar(actions = {
        TextButton(onClick = {}) { Text("Alarm") }
    })
}