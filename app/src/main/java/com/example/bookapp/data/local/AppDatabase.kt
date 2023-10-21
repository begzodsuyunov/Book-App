package com.example.bookapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookapp.data.local.Entity.BookEntity
import com.example.bookapp.data.local.dao.BookDao


@Database(entities = [BookEntity::class], version = 3, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
}