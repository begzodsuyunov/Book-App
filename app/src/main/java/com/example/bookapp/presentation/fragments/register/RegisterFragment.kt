package com.example.bookapp.presentation.fragments.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentRegisterBinding
import com.example.bookapp.presentation.viewmodels.RegisterViewModel
import com.example.bookapp.presentation.viewmodels.impl.RegisterViewModelImpl
import com.example.bookapp.utils.snackBar
import com.example.bookapp.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks

@OptIn(FlowPreview::class)
@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val viewModel: RegisterViewModel by viewModels<RegisterViewModelImpl>()

    private val viewBinding: FragmentRegisterBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.tvNotRegister.clicks()
            .debounce(100L)
            .onEach {
                viewModel.navigateToLogin()
            }.launchIn(lifecycleScope)

        viewBinding.apply {
            btnRegister.clicks()
                .debounce(100L)
                .onEach {
                    val name = inputName.text.toString()
                    val password = inputPassword.text.toString()
                    val confirmPassword = inputConfirmPassword.text.toString()
                    if (name.isNotEmpty()) {
                        if (password.isNotEmpty()) {
                            if (password != confirmPassword) {
                                snackBar(resources.getString(R.string.error_password_input))
                            } else {
                                viewModel.register(name, password)
                            }
                        } else {
                            toast("Password is required")
                        }
                    } else {
                        toast("Name is required")
                    }
                }.launchIn(lifecycleScope)
        }
    }
}