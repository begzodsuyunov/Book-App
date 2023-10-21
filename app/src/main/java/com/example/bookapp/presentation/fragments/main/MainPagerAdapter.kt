package com.example.bookapp.presentation.fragments.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bookapp.presentation.fragments.favorite.FavoriteFragment
import com.example.bookapp.presentation.fragments.main.pages.home.HomePageFragment
import com.example.bookapp.presentation.fragments.main.pages.profile.ProfileFragment
import com.example.bookapp.presentation.fragments.main.pages.saved.SavedPageFragment

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 4


    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> HomePageFragment()
        1 -> SavedPageFragment()
        2 -> FavoriteFragment()
//        3 -> SearchScreen()
        else -> ProfileFragment()    }
}