package com.example.bookapp.presentation.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentLoginBinding
import com.example.bookapp.presentation.viewmodels.LoginViewModel
import com.example.bookapp.presentation.viewmodels.impl.LoginViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks


@OptIn(FlowPreview::class)
@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()

    private val viewBinding: FragmentLoginBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.btnLogin.clicks().debounce(100L).onEach {
            viewModel.login(
                viewBinding.inputName.text.toString(),
                viewBinding.inputPassword.text.toString()
            )
        }.launchIn(lifecycleScope)

        viewBinding.tvNotRegister.clicks().debounce(100L).onEach {
            viewModel.navigateToRegister()
        }.launchIn(lifecycleScope)
    }
}