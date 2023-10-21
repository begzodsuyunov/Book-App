package com.example.bookapp.directions.impl

import com.example.bookapp.data.local.Entity.BookEntity
import com.example.bookapp.directions.BookDetailsFragmentDirection
import com.example.bookapp.navigation.Navigator
import com.example.bookapp.presentation.fragments.details.BookDetailsFragmentDirections
import javax.inject.Inject

class BookDetailsFragmentDirectionImpl  @Inject constructor(
    private val navigator: Navigator
) : BookDetailsFragmentDirection {
    override suspend fun navigateToReadBook(bookEntity: BookEntity) {
        navigator.navigateTo(BookDetailsFragmentDirections.actionBookDetailsFragmentToReadFragment(bookEntity))
    }
}