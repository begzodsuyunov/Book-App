package com.example.bookapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookapp.data.local.Entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(bookEntity: BookEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBooks(list: List<BookEntity>)

    @Update
    suspend fun updateBooks(bookEntity: BookEntity)

    @Delete
    suspend fun deleteBooks(bookEntity: BookEntity)

    @Query("SELECT*FROM books")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT*FROM books WHERE isDownload==1")
    fun getAllDownloadBooks(): Flow<List<BookEntity>>

    @Query("SELECT*FROM books WHERE id=:id LIMIT 1")
    fun getBooksById(id: String): Flow<BookEntity>


}