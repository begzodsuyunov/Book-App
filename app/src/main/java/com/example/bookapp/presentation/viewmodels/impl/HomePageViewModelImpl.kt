package com.example.bookapp.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.base.BaseViewModel
import com.example.bookapp.data.local.Entity.BookEntity
import com.example.bookapp.directions.MainPageFragmentDirection
import com.example.bookapp.presentation.viewmodels.HomePageViewModel
import com.example.bookapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModelImpl @Inject constructor(
    private val repository: BookRepository,
    private val baseViewModel: BaseViewModel,
    private val direction: MainPageFragmentDirection
) : HomePageViewModel, ViewModel() {

    override val booksStateFlow = MutableSharedFlow<List<BookEntity>>()


    override fun openBookDetails(bookEntity: BookEntity) {
        viewModelScope.launch {
            direction.navigateToBookDetails(bookEntity)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            baseViewModel.loadingSharedFlow.emit(true)
            repository.refreshData().collectLatest {
                it.onSuccess {

                }.onMessage { message ->
                    baseViewModel.messageSharedFlow.emit(message)
                }.onError { error ->
                    baseViewModel.errorSharedFlow.emit(error.localizedMessage!!)
                }
            }
        }

        viewModelScope.launch {
            repository.getAllBooks().collectLatest {
                baseViewModel.loadingSharedFlow.emit(false)
                booksStateFlow.emit(it)
            }
        }
    }
}