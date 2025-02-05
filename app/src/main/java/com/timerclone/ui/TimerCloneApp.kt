package com.timerclone.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.timerclone.ui.route.DefaultNavigationViewModel
import com.timerclone.ui.route.Destination
import com.timerclone.ui.route.NavigationAction
import com.timerclone.ui.route.Navigator
import com.timerclone.ui.screen.alarm.AlarmScreen
import com.timerclone.ui.utils.ObserverAsEvents

@Composable
fun TimerCloneApp(viewModel: Navigator = viewModel<DefaultNavigationViewModel>()) {
    val localNavController = rememberNavController()

    ObserverAsEvents(flow = viewModel.navigationAction) {
        when (it) {
            is NavigationAction.Navigate -> localNavController.navigate(it.destination) {
                it.navOptions(this)
            }

            NavigationAction.NavigateUp -> localNavController.navigateUp()
        }
    }
    NavHost(
        navController = localNavController,
        startDestination = viewModel.startDestination
    ) {
        composable<Destination.Alarm> { AlarmScreen( /* ... */) }
    }
}