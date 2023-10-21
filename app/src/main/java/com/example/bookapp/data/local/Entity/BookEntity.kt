package com.example.bookapp.data.local.Entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val author: String,
    val pages: Int,
    val description: String,
    val download: Int = 0,
    val currentPage: Int = 0,
    val isDownload: Int = 0,
    val downloadUrl: String = "",
    val imageUrl: String,
    val storageUrl: String,
    val favorite:Int=0,
) : Parcelable