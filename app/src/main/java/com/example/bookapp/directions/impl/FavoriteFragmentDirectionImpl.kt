package com.example.bookapp.directions.impl

import com.example.bookapp.data.local.Entity.BookEntity
import com.example.bookapp.directions.FavoriteFragmentDirection
import com.example.bookapp.navigation.Navigator
import com.example.bookapp.presentation.fragments.main.MainFragmentDirections
import javax.inject.Inject

class FavoriteFragmentDirectionImpl  @Inject constructor(
    private val navigator: Navigator
) : FavoriteFragmentDirection {
    override suspend fun navigateToBookDetails(bookEntity: BookEntity) {
        navigator.navigateTo(MainFragmentDirections.actionMainFragmentToBookDetailsFragment(bookEntity))
    }
}