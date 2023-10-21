package com.example.bookapp.presentation.viewmodels

interface LoginViewModel {

    fun login(name: String, password: String)

    fun navigateToRegister()

}