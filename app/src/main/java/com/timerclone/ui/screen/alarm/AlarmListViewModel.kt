package com.timerclone.ui.screen.alarm

import androidx.lifecycle.ViewModel
import com.timerclone.ui.route.DefaultNavigationViewModel
import com.timerclone.ui.route.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor(
    private val navigatorViewModel: DefaultNavigationViewModel,
) : ViewModel() {
    fun goToAddAlarmScreen() {
        navigatorViewModel.navigate(Destination.AddAlarm)
    }
}