package com.example.bookapp.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.local.Entity.BookEntity
import com.example.bookapp.presentation.viewmodels.ReadBookViewModel
import com.example.bookapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadBookViewModelImpl @Inject constructor(
    private val repository: BookRepository,
) : ReadBookViewModel, ViewModel() {

    override fun updateBook(bookEntity: BookEntity) {
        viewModelScope.launch {
            repository.updateBook(bookEntity)
        }    }
}