package com.example.bookapp.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.local.Entity.BookEntity
import com.example.bookapp.directions.MainPageFragmentDirection
import com.example.bookapp.presentation.viewmodels.SaveViewModel
import com.example.bookapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModelImpl @Inject constructor(
    private val repository: BookRepository,
    private val direction: MainPageFragmentDirection
) : SaveViewModel, ViewModel() {

    override val getAllSavedBooks = MutableStateFlow<List<BookEntity>>(emptyList())

    override fun openBookDetails(bookEntity: BookEntity) {
        viewModelScope.launch {
            direction.navigateToBookDetails(bookEntity)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllSavedBooks().collectLatest {
                getAllSavedBooks.emit(it)
            }
        }
    }
}