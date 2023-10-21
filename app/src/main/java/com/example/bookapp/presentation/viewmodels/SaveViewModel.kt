package com.example.bookapp.presentation.viewmodels

import com.example.bookapp.data.local.Entity.BookEntity
import kotlinx.coroutines.flow.StateFlow

interface SaveViewModel {
    val getAllSavedBooks: StateFlow<List<BookEntity>>

    fun openBookDetails(bookEntity: BookEntity)

}