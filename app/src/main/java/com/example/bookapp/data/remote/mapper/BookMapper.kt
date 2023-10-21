package com.example.bookapp.data.remote.mapper

import com.example.bookapp.data.remote.models.BookData
import com.example.bookapp.data.remote.models.UserData
import com.google.firebase.firestore.DocumentSnapshot

object BookMapper {

    fun DocumentSnapshot.toBookData() = BookData(
        id = this.id,
        name = this["name"].toString(),
        author = this["author"].toString(),
        pages = this["pages"].toString().toInt(),
        description = this["description"].toString(),
        imageUrl = this["image_url"].toString(),
        storageUrl = this["storage_url"].toString()
    )
    fun DocumentSnapshot.toUserData() = UserData(
        id = id,
        name = this["name"].toString(),
        password = this["password"].toString(),
        image = this["image"].toString()
    )
}