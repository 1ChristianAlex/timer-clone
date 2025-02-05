package com.timerclone.di

import com.timerclone.ui.route.DefaultNavigationViewModel
import com.timerclone.ui.route.Destination
import com.timerclone.ui.route.Navigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {
    @Provides
    @Singleton
    fun provideDefaultNavigation(): DefaultNavigationViewModel {
        return DefaultNavigationViewModel(startDestination = Destination.Alarm)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class SingletonBindModule {
    @Binds
    @Singleton
    abstract fun bindDefaultNavigation(defaultNavigationViewModel: DefaultNavigationViewModel): Navigator
}