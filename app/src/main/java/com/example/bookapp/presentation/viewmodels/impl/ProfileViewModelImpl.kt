package com.example.bookapp.presentation.viewmodels.impl

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.base.BaseViewModel
import com.example.bookapp.data.prefs.MySharedPref
import com.example.bookapp.presentation.viewmodels.ProfileViewModel
import com.example.bookapp.repository.BookRepository
import com.example.bookapp.utils.hasConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val sharedPreference: MySharedPref,
    private val repository: BookRepository,
    private val baseViewModel: BaseViewModel
) : ViewModel(), ProfileViewModel {

    override val nameLiveData: MutableLiveData<String> =
        MutableLiveData(sharedPreference.name)

    override val imageLiveData: MutableLiveData<String> =
        MutableLiveData(sharedPreference.image)

    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val changeNameLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val changeImageLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val aboutUsLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val contactLiveData: MutableLiveData<Unit> = MutableLiveData()
    override val supportLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun changeName() {
        changeNameLiveData.postValue(Unit)
    }

    override fun changeImage() {
        changeImageLiveData.postValue(Unit)
    }

    override fun aboutClicked() {
        aboutUsLiveData.postValue(Unit)
    }

    override fun helpClicked() {
        contactLiveData.postValue(Unit)
    }

    override fun supportClicked() {
        supportLiveData.postValue(Unit)
    }

    override fun setName(name: String) {
        viewModelScope.launch {
            sharedPreference.name = name
            nameLiveData.value = (name)

        }
    }

    override fun setImage() {
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setImage(str: String) {
        viewModelScope.launch {
            if (hasConnection()) {
                val def = repository.uploadImage(str)
                sharedPreference.image = def
                imageLiveData.postValue(def)
                repository.updateUser()
            } else {
                baseViewModel.messageSharedFlow.emit("No internet connection")
            }
        }
    }

    override fun backClick() {
        backLiveData.postValue(Unit)
    }
}