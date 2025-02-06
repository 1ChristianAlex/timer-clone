package com.timerclone.ui.screen.alarm.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.timerclone.R
import com.timerclone.ui.screen.alarm.AlarmListViewModel
import com.timerclone.ui.theme.spacing
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


@Composable
fun AlarmList(
    enableNestScroll: Boolean,
    enableNestScrollTitle: Boolean,
    alarmListViewModel: AlarmListViewModel,
) {
    val scrollNestedState = rememberScrollState()
    val alarmList = remember {
        mutableStateListOf(
            AlarmItem(
                name = "Gaming", time = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ), isEnable = true, daysWeek = listOf(1, 3, 5)
            ), AlarmItem(
                name = "Work",
                time = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ),
                isEnable = true,

                ), AlarmItem(
                name = "Work", time = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ), isEnable = true
            ), AlarmItem(
                name = "Work", time = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ), isEnable = true
            ), AlarmItem(
                name = "Work", time = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ), isEnable = true, daysWeek = listOf(2)
            ), AlarmItem(
                name = "Work", time = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ), isEnable = true
            ), AlarmItem(
                name = "Work", time = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ), isEnable = true
            ), AlarmItem(
                name = "Work", time = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ), isEnable = true
            ), AlarmItem(
                name = "Stop Work", time = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ), isEnable = true
            ), AlarmItem(
                name = "Gaming", time = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ), isEnable = true
            ), AlarmItem(
                name = "Work", time = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ), isEnable = true
            ), AlarmItem(
                name = "Stop Work", time = Clock.System.now().toLocalDateTime(
                    TimeZone.currentSystemDefault()
                ), isEnable = true
            )
        )
    }

    val alpha by animateFloatAsState(
        targetValue = if (enableNestScrollTitle) 1f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = ""
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.md)

    ) {
        Text(
            text = stringResource(R.string.alarm),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.alpha(alpha)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                alarmListViewModel.goToAddAlarmScreen()
            }) {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = stringResource(R.string.add_new_alarm),
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            AlarmListMenu()
        }
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollNestedState, enableNestScroll)
    ) {
        repeat(alarmList.count()) {
            val item = alarmList[it]

            AlarmCardItem(item)
        }
    }
}

