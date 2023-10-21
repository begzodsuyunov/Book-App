package com.example.bookapp.presentation.fragments.main.pages.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentHomePageBinding
import com.example.bookapp.presentation.viewmodels.HomePageViewModel
import com.example.bookapp.presentation.viewmodels.impl.HomePageViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomePageFragment : Fragment(R.layout.fragment_home_page) {

    private val viewModel: HomePageViewModel by viewModels<HomePageViewModelImpl>()

    private val viewBinding: FragmentHomePageBinding by viewBinding()

    private val adapter: BooksAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BooksAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listBooks.adapter = adapter
        viewModel.booksStateFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.openBookDetails(it)
        }

    }

}