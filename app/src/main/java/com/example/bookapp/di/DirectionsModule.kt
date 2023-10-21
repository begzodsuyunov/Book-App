package com.example.bookapp.di

import com.example.bookapp.directions.BookDetailsFragmentDirection
import com.example.bookapp.directions.FavoriteFragmentDirection
import com.example.bookapp.directions.LoginFragmentDirection
import com.example.bookapp.directions.MainPageFragmentDirection
import com.example.bookapp.directions.RegisterFragmentDirection
import com.example.bookapp.directions.SplashFragmentDirection
import com.example.bookapp.directions.impl.BookDetailsFragmentDirectionImpl
import com.example.bookapp.directions.impl.FavoriteFragmentDirectionImpl
import com.example.bookapp.directions.impl.LoginFragmentDirectionImpl
import com.example.bookapp.directions.impl.MainPageFragmentDirectionImpl
import com.example.bookapp.directions.impl.RegisterFragmentDirectionImpl
import com.example.bookapp.directions.impl.SplashFragmentDirectionImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {

    @Binds
    fun bindSplashScreen(impl: SplashFragmentDirectionImpl): SplashFragmentDirection

    @Binds
    fun bindLoginScreen(impl: LoginFragmentDirectionImpl): LoginFragmentDirection

    @Binds
    fun bindRegisterScreen(impl: RegisterFragmentDirectionImpl): RegisterFragmentDirection

    @Binds
    fun bindMainScreen(impl: MainPageFragmentDirectionImpl): MainPageFragmentDirection

    @Binds
    fun bindFavoriteScreen(impl: FavoriteFragmentDirectionImpl): FavoriteFragmentDirection

    @Binds
    fun bindBookDetailsScreenImpl(impl: BookDetailsFragmentDirectionImpl): BookDetailsFragmentDirection

}