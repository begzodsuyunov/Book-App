package com.example.bookapp.directions

interface LoginFragmentDirection {

    suspend fun navigateToMain()

    suspend fun navigateToRegister()
}