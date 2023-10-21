package com.example.bookapp.directions

import com.example.bookapp.data.local.Entity.BookEntity

interface FavoriteFragmentDirection {
    suspend fun navigateToBookDetails(bookEntity: BookEntity)

}