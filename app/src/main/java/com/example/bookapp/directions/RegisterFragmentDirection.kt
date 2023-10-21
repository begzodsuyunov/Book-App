package com.example.bookapp.directions

interface RegisterFragmentDirection {
    suspend fun navigateToMain()

    suspend fun navigateToLogin()
}