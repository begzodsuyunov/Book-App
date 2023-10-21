package com.example.bookapp.di

import com.example.bookapp.repository.BookRepository
import com.example.bookapp.repository.impl.BookRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindsBookRepository(impl: BookRepositoryImpl): BookRepository
}