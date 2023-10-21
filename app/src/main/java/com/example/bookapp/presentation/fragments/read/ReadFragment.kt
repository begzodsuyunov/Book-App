package com.example.bookapp.presentation.fragments.read

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.App
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentReadBinding
import com.example.bookapp.presentation.viewmodels.ReadBookViewModel
import com.example.bookapp.presentation.viewmodels.impl.ReadBookViewModelImpl
import com.example.bookapp.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ReadFragment : Fragment(R.layout.fragment_read) {

    private val viewModel: ReadBookViewModel by viewModels<ReadBookViewModelImpl>()


    private val args: ReadFragmentArgs by navArgs()

    private var maxBook = 0
    private val viewBinding: FragmentReadBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        maxBook = args.books.currentPage

        viewBinding.tvBookName.text = args.books.name
        viewBinding.tvBookName.setSingleLine()

        viewBinding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }

        try {
            val uri = FileProvider.getUriForFile(
                requireContext(), "com.example.bookapp" + ".provider",
                File(App.instance.filesDir, args.books.name + ".pdf")
            )

            val isNight = Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
            viewBinding.pdfViewer
                .fromUri(uri)
                .nightMode(isNight)
                .defaultPage(args.books.currentPage)
                .pageSnap(true)
                .onPageScroll { page, _ ->
                    if (page > maxBook) {
                        maxBook = page
                        viewModel.updateBook(args.books.copy(currentPage = maxBook))
                    }
                }
                .load()
        } catch (e: Exception) {
            viewModel.updateBook(
                bookEntity = args.books.copy(
                    isDownload = args.books.isDownload,
                    currentPage = 0
                )
            )
            showErrorDialog(e.message!!) {
                findNavController().navigateUp()
            }
        }
    }

}