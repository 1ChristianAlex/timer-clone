package com.timerclone.ui.screen.alarm.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.timerclone.ui.theme.spacing
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames

data class AlarmItem(
    val name: String,
    val time: LocalDateTime,
    val isEnable: Boolean,
    val daysWeek: List<Int>? = listOf(),
)

@Composable
fun AlarmCardItem(alarmItem: AlarmItem) {
    alarmItem.run {

        var checked by remember { mutableStateOf(isEnable) }
        val allDaysWeekAbrev = (1..7).map {
            DayOfWeek(it).name.toList().first()
        }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ), modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.md)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm / 2)) {
                    Text(
                        text = name,
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = LocalDateTime.Format {
                            hour()
                            chars(":")
                            minute()
                        }.format(time),
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (daysWeek.isNullOrEmpty()) {
                        Text(
                            text = LocalDateTime.Format {
                                dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
                                chars(", ")
                                monthName(MonthNames.ENGLISH_ABBREVIATED)
                                chars(" ")
                                dayOfMonth()
                            }.format(time),
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    } else {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(3.dp),
                            modifier = Modifier.padding(end = MaterialTheme.spacing.md)
                        ) {
                            val styleHasDay = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.Red,
                                fontWeight = FontWeight.Black
                            )
                            repeat(allDaysWeekAbrev.count()) {
                                val itemWeekAbrev = allDaysWeekAbrev[it]
                                Text(
                                    text = itemWeekAbrev.toString(),
                                    style = if (daysWeek.contains(it + 1)) styleHasDay else MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }


                    Switch(modifier = Modifier.scale(1f), checked = checked, onCheckedChange = {
                        checked = it
                    })
                }
            }
        }
    }
}