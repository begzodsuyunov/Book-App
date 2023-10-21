package com.example.bookapp.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.base.BaseViewModel
import com.example.bookapp.data.prefs.MySharedPref
import com.example.bookapp.data.remote.models.UserData
import com.example.bookapp.directions.LoginFragmentDirection
import com.example.bookapp.presentation.fragments.login.LoginFragment
import com.example.bookapp.presentation.viewmodels.LoginViewModel
import com.example.bookapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val repository: BookRepository,
    private val mySharedPref: MySharedPref,
    private val direction: LoginFragmentDirection,
    private val base: BaseViewModel
) : LoginViewModel, ViewModel(){
    override fun login(name: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            base.loadingSharedFlow.emit(true)
            val res = repository.login(UserData(name = name, password = password))
            res.onError {
                base.errorSharedFlow.emit(it.message!!)
            }.onSuccess {
                mySharedPref.name = name
                mySharedPref.password = password
                mySharedPref.image = it.image
                mySharedPref.id = it.id

                direction.navigateToMain()
            }.onMessage {
                base.messageSharedFlow.emit(it)
            }
            base.loadingSharedFlow.emit(false)
        }
    }

    override fun navigateToRegister() {
        viewModelScope.launch {
            direction.navigateToRegister()
        }
    }
}