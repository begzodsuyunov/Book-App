package com.example.bookapp.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.base.BaseViewModel
import com.example.bookapp.data.prefs.MySharedPref
import com.example.bookapp.data.remote.models.UserData
import com.example.bookapp.directions.RegisterFragmentDirection
import com.example.bookapp.presentation.viewmodels.RegisterViewModel
import com.example.bookapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(
    private val repository: BookRepository,
    private val directions: RegisterFragmentDirection,
    private val mySharedPref: MySharedPref,
    private val base: BaseViewModel

) : RegisterViewModel, ViewModel() {
    override fun register(name: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            base.loadingSharedFlow.emit(true)
            repository.registerUser(userData = UserData(name = name, password = password))
                .collectLatest {
                    base.loadingSharedFlow.emit(false)
                    it.onSuccess {
                        mySharedPref.name = name
                        mySharedPref.password = password
                        mySharedPref.id = it.id
                        directions.navigateToMain()
                    }.onMessage { message ->
                        base.messageSharedFlow.emit(message)
                    }.onError { error ->
                        base.errorSharedFlow.emit(error.localizedMessage ?: "Unknown error")
                    }
                }
        }
    }
    override fun navigateToLogin() {
        viewModelScope.launch {
            directions.navigateToLogin()
        }    }
}