package com.example.bookapp.data.remote.models

import com.example.bookapp.data.local.Entity.BookEntity

data class BookData(
    val id: String,
    val name: String,
    val author: String,
    val pages: Int,
    val description: String,
    val imageUrl: String,
    val storageUrl: String
) {
    fun toBookEntity() = BookEntity(
        id = id,
        name = name,
        author = author,
        pages = pages,
        description = description,
        imageUrl = imageUrl,
        storageUrl = storageUrl,
    )
}