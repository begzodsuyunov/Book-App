package com.example.bookapp.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.prefs.MySharedPref
import com.example.bookapp.directions.SplashFragmentDirection
import com.example.bookapp.presentation.viewmodels.SplashViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val direction: SplashFragmentDirection,
    private val mySharedPref: MySharedPref
) : SplashViewModel, ViewModel(){
    override fun navigate() {
        viewModelScope.launch {
            if (mySharedPref.password.isEmpty()) {
                direction.navigateToLogin()
            } else {
                direction.navigateToHome()
            }
        }    }
}