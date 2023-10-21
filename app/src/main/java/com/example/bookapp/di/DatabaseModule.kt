package com.example.bookapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.bookapp.data.local.AppDatabase
import com.example.bookapp.data.local.dao.BookDao
import com.example.bookapp.data.prefs.MySharedPref
import com.example.bookapp.data.remote.BookApi
import com.example.bookapp.data.remote.impl.BookApiImpl
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "book_data"

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, DATABASE_NAME)
            .build()

    @[Provides Singleton]
    fun provideBookDao(appDatabase: AppDatabase): BookDao = appDatabase.bookDao()

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("app_data", Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun provideSharedPrefs(
        @ApplicationContext context: Context,
        sharedPreferences: SharedPreferences
    ): MySharedPref =
        MySharedPref(context, sharedPreferences)

    @[Provides Singleton]
    fun provideFireStore(): FirebaseFirestore = Firebase.firestore

    @[Provides Singleton]
    fun provideBookApi(fireStore: FirebaseFirestore, mySharedPref: MySharedPref): BookApi = BookApiImpl(fireStore,mySharedPref)


}