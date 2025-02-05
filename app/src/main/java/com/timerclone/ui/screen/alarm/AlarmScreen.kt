package com.timerclone.ui.screen.alarm

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.timerclone.R
import com.timerclone.ui.screen.alarm.components.AlarmList
import com.timerclone.ui.theme.spacing


@Composable
fun AlarmScreen() {
    val localDensity = LocalDensity.current
    val screenSize = LocalConfiguration.current.screenHeightDp
    val scrollState = rememberScrollState()
    var hourVisible by remember { mutableStateOf(false) }
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }
    var enableNestScroll by remember {
        mutableStateOf(false)
    }
    var enableNestScrollTitle by remember {
        mutableStateOf(false)
    }

    fun roundedByHundred(value: Double): Int {
        return (value / 100).toInt() * 100
    }

    LaunchedEffect(scrollState.value) {
        hourVisible = !(scrollState.value > 0 && (scrollState.value * 1.8) > columnHeightDp.value)
        val scrollPass = scrollState.value + screenSize * 0.3
        enableNestScroll = roundedByHundred(scrollPass) >= roundedByHundred(screenSize.toDouble())
        println(scrollPass)
        println(screenSize)
        enableNestScrollTitle = scrollState.value > 0
    }

    AnimatedVisibility(
        visible = hourVisible,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.onGloballyPositioned { coordinates ->
                // Set column height using the LayoutCoordinates
                columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
            }) {
            Spacer(Modifier.height((screenSize * 0.13).dp))
            Text(
                text = "${stringResource(R.string.alarm_in)} 2 hours 20 minutes",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.6f)
            )
            Text(
                "Thu, Jan 30, 14:00",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,

                modifier = Modifier.fillMaxWidth()
            )
        }


        Spacer(Modifier.height((screenSize * 0.1).dp))
    }
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(horizontal = MaterialTheme.spacing.md)
            .height((screenSize + columnHeightDp.value).dp)
    ) {
        Spacer(Modifier.height((screenSize * 0.3).dp))
        AlarmList(
            enableNestScroll = enableNestScroll,
            enableNestScrollTitle = enableNestScrollTitle
        )
    }
}