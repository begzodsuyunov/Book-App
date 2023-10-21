package com.example.bookapp.directions.impl

import com.example.bookapp.directions.SplashFragmentDirection
import com.example.bookapp.navigation.Navigator
import com.example.bookapp.presentation.fragments.splash.SplashFragmentDirections
import javax.inject.Inject

class SplashFragmentDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : SplashFragmentDirection{

    override suspend fun navigateToHome() {
        navigator.navigateTo(SplashFragmentDirections.actionSplashFragmentToMainFragment())
    }

    override suspend fun navigateToLogin() {
        navigator.navigateTo(SplashFragmentDirections.actionSplashFragmentToLoginFragment())    }
}