package com.example.bookapp.repository

import com.example.bookapp.data.local.Entity.BookEntity
import com.example.bookapp.data.remote.models.ResultData
import com.example.bookapp.data.remote.models.UserData
import com.example.bookapp.repository.impl.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    fun getAllBooks(): Flow<List<BookEntity>>

    fun isDownloaded(bookEntity: BookEntity): Flow<Boolean>

    fun getAllSavedBooks(): Flow<List<BookEntity>>

    fun refreshData(): Flow<ResultData<Boolean>>

    fun downloadBook(bookEntity: BookEntity): Flow<ResultData<Result>>

    suspend fun login(userData: UserData): ResultData<UserData>

    suspend fun registerUser(userData: UserData): Flow<ResultData<UserData>>

    suspend fun updateBook(bookEntity: BookEntity)

    suspend fun getBookById(id: String): Flow<BookEntity>

    suspend fun updateUser()

    suspend fun uploadImage(path:String):String
}