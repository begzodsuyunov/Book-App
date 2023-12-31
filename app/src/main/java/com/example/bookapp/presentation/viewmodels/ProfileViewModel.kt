package com.example.bookapp.presentation.viewmodels

import androidx.lifecycle.LiveData

interface ProfileViewModel {

    val nameLiveData: LiveData<String>

    val imageLiveData: LiveData<String>

    val backLiveData: LiveData<Unit>

    val changeNameLiveData: LiveData<Unit>

    val changeImageLiveData: LiveData<Unit>

    val aboutUsLiveData: LiveData<Unit>

    val contactLiveData: LiveData<Unit>

    val supportLiveData: LiveData<Unit>



    fun changeName()

    fun changeImage()

    fun aboutClicked()

    fun helpClicked()

    fun supportClicked()

    fun setName(name: String)

    fun setImage()

    fun backClick()

    fun setImage(str: String)

}