package com.example.bookapp.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.base.BaseViewModel
import com.example.bookapp.data.local.Entity.BookEntity
import com.example.bookapp.directions.BookDetailsFragmentDirection
import com.example.bookapp.presentation.viewmodels.BookDetailsViewModel
import com.example.bookapp.repository.BookRepository
import com.example.bookapp.repository.impl.Result.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModelImpl @Inject constructor(
    private val repository: BookRepository,
    private val baseViewModel: BaseViewModel,
    private val direction: BookDetailsFragmentDirection
) : BookDetailsViewModel, ViewModel() {


    override val bookFlow = MutableSharedFlow<BookEntity>()
    override val isDownload = MutableSharedFlow<Boolean>()

    override fun getBooksById(bookEntity: BookEntity) {
        viewModelScope.launch {
            repository.getBookById(bookEntity.id).collect {
                bookFlow.emit(it)
            }
        }
    }

    override fun isDownloaded(bookEntity: BookEntity) {
        repository.isDownloaded(bookEntity)
        viewModelScope.launch {
            repository.isDownloaded(bookEntity).collect {
                isDownload.emit(it)
            }
        }
    }

    override fun downloadBook(bookEntity: BookEntity) {
        viewModelScope.launch {
            repository.downloadBook(bookEntity).collectLatest {
                it.onSuccess { result ->
                    when (result) {
                        Start -> {

                        }
                        is Progress -> {
                            repository.updateBook(bookEntity.copy(download = ((result.current * 100) / result.total).toInt()))
                        }
                        is Error -> {
                            baseViewModel.errorSharedFlow.emit(result.message)
                        }
                        is End -> {
                            repository.updateBook(
                                bookEntity.copy(
                                    isDownload = 1,
                                    downloadUrl = result.filename
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    override fun openReadBook(bookEntity: BookEntity) {
        viewModelScope.launch {
            direction.navigateToReadBook(bookEntity)
        }
    }

    override fun updateBook(bookEntity: BookEntity) {
        viewModelScope.launch {
            repository.updateBook(bookEntity)
        }
    }
}