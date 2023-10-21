package com.example.bookapp.data.remote.models

import java.util.UUID

data class UserData (
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val password: String,
    val image:String=""
)