package com.example.bookapp.data.remote

import com.example.bookapp.data.remote.models.BookData
import com.example.bookapp.data.remote.models.ResultData
import com.example.bookapp.data.remote.models.UserData
import kotlinx.coroutines.flow.Flow

interface BookApi {

    fun getAllBooks(): Flow<ResultData<List<BookData>>>

    suspend fun updateRatings(bookData: BookData): ResultData<BookData>

    suspend fun registerUser(userData: UserData): Flow<ResultData<UserData>>

    suspend fun loginUser(userData: UserData): ResultData<UserData>

    suspend fun updateUser()

    suspend fun uploadImage(path:String):String

}