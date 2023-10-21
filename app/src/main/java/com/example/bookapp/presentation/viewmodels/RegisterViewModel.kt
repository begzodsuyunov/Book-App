package com.example.bookapp.presentation.viewmodels

interface RegisterViewModel {

    fun register(name:String,password:String)

    fun navigateToLogin()
}