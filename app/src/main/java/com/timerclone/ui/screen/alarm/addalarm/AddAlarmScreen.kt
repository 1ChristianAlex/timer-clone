package com.timerclone.ui.screen.alarm.addalarm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.timerclone.ui.screen.alarm.addalarm.components.AddTimerForm
import com.timerclone.ui.screen.alarm.addalarm.components.PagerSelectTime
import com.timerclone.ui.theme.spacing

@Composable
fun AddAlarmScreen(
    addAlarmViewModel: AddAlarmViewModel,
) {
    val screenHeightSize = LocalConfiguration.current.screenHeightDp
    val rowPagerHeight = screenHeightSize * 0.4

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height((rowPagerHeight).dp),
            horizontalArrangement = Arrangement.spacedBy(
                MaterialTheme.spacing.md, Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            val pageModifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16))
                .padding(
                    MaterialTheme.spacing.md
                )

            PagerSelectTime(rowPagerHeight, pageModifier, 23, ::println)
            Text(
                ":",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
            )
            PagerSelectTime(rowPagerHeight, pageModifier, 59, ::println)
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEndPercent = 4, topStartPercent = 4))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .weight(1f)
                .fillMaxWidth(),
        ) {
            AddTimerForm()
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomButtons(text = "Cancel", modifier = Modifier.weight(1f), onClick = {
                addAlarmViewModel.backToAlarmScreen()
            })
            BottomButtons(text = "Save", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun BottomButtons(modifier: Modifier = Modifier, onClick: () -> Unit = {}, text: String) {
    TextButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15),
        contentPadding = PaddingValues(MaterialTheme.spacing.md)
    ) {
        Text(
            text = text, style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold
            ),

            textAlign = TextAlign.Center
        )
    }
}