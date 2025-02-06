package com.timerclone.ui.screen.alarm.addalarm.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.timerclone.ui.theme.spacing

@Composable
fun PagerSelectTime(
    rowPagerHeight: Double,
    modifier: Modifier,
    maxTime: Int,
    onClick: (value: Int) -> Unit = {},
) {
    val maxLoops = 10
    val minutesValues = remember {
        mutableListOf<String>().apply {
            add("00")

            for (item in 1..maxTime) {
                item.toString().let {

                    add(if (it.length == 1) "0${it}" else it)
                }
            }
        }.toList()
    }
    val pageCount = minutesValues.size * maxLoops

    val initialPage = minutesValues.size * (maxLoops / 2)
    val minutesPager = rememberPagerState(initialPage = initialPage - 1) { pageCount }


    fun <T> loopIteration(listItem: List<T>, index: Int): T {
        val listCount = listItem.count()
        val actualIndex = if (index < 0) {
            listCount + (index % listCount)
        } else {
            index % listCount
        }

        return listItem[actualIndex]
    }



    LaunchedEffect(minutesPager) {
        snapshotFlow { minutesPager.currentPage }.collect { page ->
            onClick(loopIteration(minutesValues, page + 1).toInt())
        }
    }

    VerticalPager(
        state = minutesPager,
        modifier = modifier,
        pageSize = PageSize.Fixed((rowPagerHeight / 3).dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        beyondViewportPageCount = 1,
    ) { page ->

        val isCurrentTime = minutesPager.currentPage + 1 == page
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height((rowPagerHeight / 3).dp - (MaterialTheme.spacing.md * 2)),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = loopIteration(minutesValues, page),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(
                        alpha = if (!isCurrentTime) 0.7f else 1f
                    )
                )
            )
        }
    }
}