package com.timerclone.ui.screen.alarm.addalarm

import androidx.lifecycle.ViewModel
import com.timerclone.ui.route.DefaultNavigationViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddAlarmViewModel @Inject constructor(private val navigatorViewModel: DefaultNavigationViewModel) :
    ViewModel() {

    fun backToAlarmScreen() {
        navigatorViewModel.navigateUp()
    }
}