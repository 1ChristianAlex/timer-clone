package com.timerclone.ui.route

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptionsBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface Navigator {
    val startDestination: Destination
    val navigationAction: Flow<NavigationAction>

    fun navigate(destination: Destination, navOptions: NavOptionsBuilder.() -> Unit = {})

    fun navigateUp()
}

@HiltViewModel
class DefaultNavigationViewModel @Inject constructor(
    override val startDestination: Destination,
) : Navigator, ViewModel() {
    private val _navigationAction = Channel<NavigationAction>()
    override val navigationAction = _navigationAction.consumeAsFlow()

    private val _currentAction = LiveData()
    override fun navigate(destination: Destination, navOptions: NavOptionsBuilder.() -> Unit) {
        viewModelScope.launch {
            _navigationAction.send(NavigationAction.Navigate(destination, navOptions))
        }
    }

    override fun navigateUp() {
        viewModelScope.launch {
            _navigationAction.send(NavigationAction.NavigateUp)
        }
    }

}