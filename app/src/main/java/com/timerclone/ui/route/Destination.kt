package com.timerclone.ui.route

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object AlarmNav : Destination

    @Serializable
    data object Alarm : Destination

    @Serializable
    data object AddAlarm : Destination

    @Serializable
    data object WorldClock : Destination

    @Serializable
    data object Stopwatch : Destination

    @Serializable
    data object Timer : Destination
}