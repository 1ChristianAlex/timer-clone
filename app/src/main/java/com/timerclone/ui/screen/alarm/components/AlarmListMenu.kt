package com.timerclone.ui.screen.alarm.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.timerclone.R

@Composable
fun AlarmListMenu() {
    var expandedMenu by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        IconButton(onClick = { expandedMenu = true }) {
            Icon(
                Icons.Rounded.MoreVert,
                contentDescription = stringResource(R.string.more_options),
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        DropdownMenu(expanded = expandedMenu, onDismissRequest = { expandedMenu = false }) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.set_sleep_mode_schedule)) },
                onClick = { /* Handle edit! */ },
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.edit)) },
                onClick = { /* Handle settings! */ },
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.sort)) },
                onClick = { /* Handle send feedback! */ },
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.setting)) },
                onClick = { /* Handle send feedback! */ },
            )
        }
    }
}