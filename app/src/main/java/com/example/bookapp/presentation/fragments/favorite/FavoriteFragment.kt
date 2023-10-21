package com.example.bookapp.presentation.fragments.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.data.local.Entity.BookEntity
import com.example.bookapp.databinding.FragmentFavoriteBinding
import com.example.bookapp.presentation.fragments.main.pages.home.BooksAdapter
import com.example.bookapp.presentation.viewmodels.FavoriteViewModel
import com.example.bookapp.presentation.viewmodels.impl.FavoriteViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val viewModel: FavoriteViewModel by viewModels<FavoriteViewModelImpl>()

    private val viewBinding: FragmentFavoriteBinding by viewBinding()

    private val adapter: BooksAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BooksAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.listBooks.adapter = adapter
        viewModel.booksStateFlow.map {
            val a = ArrayList<BookEntity>()
            it.forEach { it1->
                if(it1.favorite==1){
                    a.add(it1)
                }
            }
            a
        }.onEach {
            if(it.size==0){
                viewBinding.imageSplash.visibility = View.VISIBLE
            }else{
                viewBinding.imageSplash.visibility = View.INVISIBLE

            }
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.openBookDetails(it)
        }
    }
}