package com.example.bookapp.di

import com.example.bookapp.base.BaseViewModel
import com.example.bookapp.base.impl.BaseViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BaseModule {

    @Provides
    @Singleton
    fun provideBaseViewModel(impl: BaseViewModelImpl): BaseViewModel = impl
}