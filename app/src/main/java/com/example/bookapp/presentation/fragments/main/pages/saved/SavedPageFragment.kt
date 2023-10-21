package com.example.bookapp.presentation.fragments.main.pages.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentSavedPageBinding
import com.example.bookapp.presentation.viewmodels.SaveViewModel
import com.example.bookapp.presentation.viewmodels.impl.SaveViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SavedPageFragment : Fragment(R.layout.fragment_saved_page) {


    private val viewModel: SaveViewModel by viewModels<SaveViewModelImpl>()

    private val viewBinding: FragmentSavedPageBinding by viewBinding()

    private val adapter: SavedAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SavedAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.listBooks.adapter = adapter

        adapter.setItemClickListener {
            viewModel.openBookDetails(it)
        }


        viewModel.getAllSavedBooks.onEach {

            if (it.size == 0) {
                viewBinding.imageSplash.visibility = View.VISIBLE
            } else {
                viewBinding.imageSplash.visibility = View.INVISIBLE

            }
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)


    }


}