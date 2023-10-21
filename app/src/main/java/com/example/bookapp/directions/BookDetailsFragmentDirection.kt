package com.example.bookapp.directions

import com.example.bookapp.data.local.Entity.BookEntity

interface BookDetailsFragmentDirection {
    suspend fun navigateToReadBook(bookEntity: BookEntity)

}