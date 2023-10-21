package com.example.bookapp.base.impl

import com.example.bookapp.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

open class BaseViewModelImpl @Inject constructor() : BaseViewModel {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()
}