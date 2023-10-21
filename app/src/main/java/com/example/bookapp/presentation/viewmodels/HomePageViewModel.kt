package com.example.bookapp.presentation.viewmodels

import com.example.bookapp.data.local.Entity.BookEntity
import kotlinx.coroutines.flow.SharedFlow

interface HomePageViewModel {

    val booksStateFlow: SharedFlow<List<BookEntity>>

    fun openBookDetails(bookEntity: BookEntity)
}