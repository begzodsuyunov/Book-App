package com.example.bookapp.directions.impl

import com.example.bookapp.data.local.Entity.BookEntity
import com.example.bookapp.directions.MainPageFragmentDirection
import com.example.bookapp.navigation.Navigator
import com.example.bookapp.presentation.fragments.main.MainFragmentDirections
import javax.inject.Inject

class MainPageFragmentDirectionImpl  @Inject constructor(
    private val navigator: Navigator
) : MainPageFragmentDirection {
    override suspend fun navigateToBookDetails(bookEntity: BookEntity) {
        navigator.navigateTo(MainFragmentDirections.actionMainFragmentToBookDetailsFragment(bookEntity))
    }
}