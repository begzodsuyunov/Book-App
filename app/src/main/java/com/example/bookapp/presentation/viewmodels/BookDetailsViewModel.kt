package com.example.bookapp.presentation.viewmodels

import com.example.bookapp.data.local.Entity.BookEntity
import kotlinx.coroutines.flow.SharedFlow

interface BookDetailsViewModel {

    val bookFlow: SharedFlow<BookEntity>
    val isDownload: SharedFlow<Boolean>

    fun getBooksById(bookEntity: BookEntity)
    fun isDownloaded(bookEntity: BookEntity)

    fun downloadBook(bookEntity: BookEntity)

    fun openReadBook(bookEntity: BookEntity)
    fun updateBook(bookEntity: BookEntity)
}