package com.example.bookapp.directions.impl

import com.example.bookapp.directions.LoginFragmentDirection
import com.example.bookapp.navigation.Navigator
import com.example.bookapp.presentation.fragments.login.LoginFragmentDirections
import javax.inject.Inject

class LoginFragmentDirectionImpl @Inject constructor(private val navigator: Navigator) :
    LoginFragmentDirection {
    override suspend fun navigateToMain() {
        navigator.navigateTo(LoginFragmentDirections.actionLoginFragmentToMainFragment())
    }

    override suspend fun navigateToRegister() {
        navigator.navigateTo(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }
}