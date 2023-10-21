package com.example.bookapp.di

import com.example.bookapp.navigation.NavigationDispatcher
import com.example.bookapp.navigation.NavigationHandler
import com.example.bookapp.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun navigator(dispatcher: NavigationDispatcher): Navigator
    @Binds
    fun navigatorHandler(dispatcher: NavigationDispatcher): NavigationHandler
}