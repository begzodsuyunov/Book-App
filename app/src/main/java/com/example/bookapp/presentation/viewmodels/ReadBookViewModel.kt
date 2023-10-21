package com.example.bookapp.presentation.viewmodels

import com.example.bookapp.data.local.Entity.BookEntity

interface ReadBookViewModel {
    fun updateBook(bookEntity: BookEntity)

}