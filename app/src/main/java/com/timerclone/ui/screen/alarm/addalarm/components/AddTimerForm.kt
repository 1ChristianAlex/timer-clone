package com.timerclone.ui.screen.alarm.addalarm.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.timerclone.R
import com.timerclone.ui.theme.spacing
import kotlinx.datetime.DayOfWeek

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTimerForm() {

    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue(
                "", TextRange(0, 100)
            )
        )
    }

    val allDaysWeekAbrev = remember {
        (1..7).map {
            DayOfWeek(it).name.toList().first()
        }
    }

    fun toggleDateModal() {
        showDatePicker = !showDatePicker
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(MaterialTheme.spacing.md)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tomorrow-Thu, Feb 6", style = MaterialTheme.typography.titleMedium)


            IconButton(onClick = { toggleDateModal() }) {
                Icon(
                    Icons.Rounded.DateRange,
                    contentDescription = stringResource(R.string.more_options),
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            if (showDatePicker) {
                DatePickerDialog(onDismissRequest = { toggleDateModal() }, confirmButton = {
                    TextButton(onClick = {
                        println(datePickerState.selectedDateMillis)
                        toggleDateModal()
                    }) {
                        Text("OK")
                    }
                }, dismissButton = {
                    TextButton(onClick = { toggleDateModal() }) {
                        Text("Cancel")
                    }
                }) {
                    DatePicker(state = datePickerState)
                }
            }

        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(allDaysWeekAbrev.count()) {
                Text(
                    text = allDaysWeekAbrev[it].toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))
        TextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                textFieldValue = newValue
            },
            placeholder = { Text(stringResource(R.string.alarm_name)) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors().copy(
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))
        TimerOptionSwitcher(
            name = stringResource(R.string.alarm_sound), currentOption = "Homecoming", enable = true
        )
        TimerOptionSwitcher(
            name = stringResource(R.string.vibration), currentOption = "Basic call", enable = true
        )
        TimerOptionSwitcher(
            name = stringResource(R.string.snooze), currentOption = "5 minutes", enable = true
        )
    }
}

@Composable
fun TimerOptionSwitcher(name: String, currentOption: String, enable: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = name, style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = currentOption,
                style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.primary)
            )
        }
        Switch(modifier = Modifier.scale(0.8f), checked = enable, onCheckedChange = {

        })
    }
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.onSecondaryContainer
    )
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))
}