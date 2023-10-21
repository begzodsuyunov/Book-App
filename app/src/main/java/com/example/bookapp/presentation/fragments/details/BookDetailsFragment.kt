package com.example.bookapp.presentation.fragments.details

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentBookDetailsBinding
import com.example.bookapp.databinding.FragmentFavoriteBinding
import com.example.bookapp.presentation.viewmodels.BookDetailsViewModel
import com.example.bookapp.presentation.viewmodels.FavoriteViewModel
import com.example.bookapp.presentation.viewmodels.impl.BookDetailsViewModelImpl
import com.example.bookapp.presentation.viewmodels.impl.FavoriteViewModelImpl
import com.example.bookapp.utils.hasPermissionApp
import com.example.bookapp.utils.setDrawableImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks

@OptIn(FlowPreview::class)
@AndroidEntryPoint
class BookDetailsFragment : Fragment(R.layout.fragment_book_details) {

    private val viewModel: BookDetailsViewModel by viewModels<BookDetailsViewModelImpl>()
    private val args: BookDetailsFragmentArgs by navArgs()
    private var isDownload = false
    private val viewBinding: FragmentBookDetailsBinding by viewBinding()


    private val permissionList: List<String> by lazy(LazyThreadSafetyMode.NONE) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            listOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE
            )
        } else {
            listOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var book = args.book
        val params = viewBinding.btnDownloadOrReading.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(48, 0, 48, 0)

        viewModel.isDownloaded(book)
        viewModel.isDownload.onEach {
            Log.d("XCXC", it.toString())
        }
        viewBinding.backToMain.setOnClickListener {
            findNavController().popBackStack()
        }
        if (book.favorite == 1) {
            viewBinding.makeFavorite.setDrawableImage(R.drawable.ic_baseline_star_24)
        }
        if (book.isDownload == 1) {
            viewBinding.makeFavorite.setOnClickListener {
                Log.d("FFFF", book.favorite.toString())
                if (book.favorite == 1) {
                    viewModel.updateBook(book.copy(favorite = 0))
                    viewBinding.makeFavorite.setDrawableImage(R.drawable.ic_baseline_star_outline_24)
                } else {
                    viewBinding.makeFavorite.setDrawableImage(R.drawable.ic_baseline_star_24)
                    viewModel.updateBook(book.copy(favorite = 1))
                }

            }
        } else {
            viewBinding.makeFavorite.visibility = View.GONE
        }
        viewBinding.apply {
            viewBinding.tvBookAuthor.text = book.author
            viewBinding.tvBookName.text = book.name
            viewBinding.tvBookPage.text = "${book.pages} bet"
            if (book.isDownload == 1) {
                viewBinding.currentPageIfRead.text =
                    "O'quvchining jori varag'i: ${book.currentPage}"
            }
            viewBinding.tvDescription.text = book.description
            Glide.with(requireContext())
                .load(book.imageUrl)
                .placeholder(R.drawable.ic_group)
                .into(imageBook)
        }

        viewBinding.btnDownloadOrReading
            .clicks().debounce(100L)
            .onEach {
                if (book.isDownload == 1) {
                    viewModel.openReadBook(book)
                } else {
                    hasPermissionApp(permissionList) {

                        if (it == 1) {
                            viewModel.downloadBook(book)
                            viewBinding.btnDownloadOrReading.text =
                                resources.getString(R.string.downloading)
                            viewBinding.btnDownloadOrReading.isEnabled = false

                            viewBinding.progressDownload.visibility = View.VISIBLE
                            viewBinding.tvDownloadPercent.visibility = View.VISIBLE
                        }
                    }
                }
            }.launchIn(lifecycleScope)

        viewModel.bookFlow.onEach { booksData ->
            isDownload = booksData.isDownload == 1
            if (isDownload) {
                viewBinding.progressDownload.visibility = View.INVISIBLE
                viewBinding.tvDownloadPercent.visibility = View.INVISIBLE
                viewBinding.btnDownloadOrReading.apply {
                    text = resources.getString(R.string.start_reading)
                    isEnabled = true
                    params.setMargins(48, 0, 0, 0)
                    viewBinding.makeFavorite.visibility = View.VISIBLE
                    viewBinding.makeFavorite.setOnClickListener {
                        Log.d("FFFF", book.favorite.toString())
                        if (book.favorite == 1) {
                            viewModel.updateBook(book.copy(favorite = 0))
                            viewBinding.makeFavorite.setDrawableImage(R.drawable.ic_baseline_star_outline_24)
                        } else {
                            viewBinding.makeFavorite.setDrawableImage(R.drawable.ic_baseline_star_24)
                            viewModel.updateBook(book.copy(favorite = 1))
                        }

                    }

                }

                book = booksData.copy()
            } else {
                viewBinding.progressDownload.progress = booksData.download
                viewBinding.tvDownloadPercent.text = "${booksData.download} %"
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.getBooksById(book)
    }

}