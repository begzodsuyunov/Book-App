package com.example.bookapp.directions.impl

import com.example.bookapp.directions.RegisterFragmentDirection
import com.example.bookapp.navigation.Navigator
import com.example.bookapp.presentation.fragments.register.RegisterFragmentDirections
import javax.inject.Inject

class RegisterFragmentDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : RegisterFragmentDirection{
    override suspend fun navigateToMain() {
        navigator.navigateTo(RegisterFragmentDirections.actionRegisterFragmentToMainFragment())
    }

    override suspend fun navigateToLogin() {
        navigator.navigateTo(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }

}