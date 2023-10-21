package com.example.bookapp.presentation.fragments.main.pages.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.bookapp.MainActivity
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentProfileBinding
import com.example.bookapp.presentation.dialogs.ChangeNameDialog
import com.example.bookapp.presentation.viewmodels.ProfileViewModel
import com.example.bookapp.presentation.viewmodels.impl.ProfileViewModelImpl
import com.example.bookapp.utils.Constants
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {


    private val binding: FragmentProfileBinding by viewBinding()

    private val viewModel: ProfileViewModel by viewModels<ProfileViewModelImpl>()

    private var mProfileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.changeNameLiveData.observe(this, changeNameObserver)
        viewModel.changeImageLiveData.observe(this, changeImageObserver)
        viewModel.contactLiveData.observe(this, contactObserver)
        viewModel.supportLiveData.observe(this, supportObserver)
        viewModel.backLiveData.observe(this, backListener)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            imgUser.setOnClickListener {
                viewModel.changeImage()

            }
            tvChangeUserName.setOnClickListener {
                viewModel.changeName()
            }
            tvChangeImageIcon.setOnClickListener {
                viewModel.changeImage()
            }

            tvHelp.setOnClickListener {
                viewModel.helpClicked()
            }
            tvSupportUs.setOnClickListener {
                viewModel.supportClicked()
            }
        }
        viewModel.nameLiveData.observe(viewLifecycleOwner, nameObserver)
        viewModel.imageLiveData.observe(viewLifecycleOwner, imageObserver)
    }

    private val nameObserver = Observer<String> {
        binding.tvUserName.text = it
    }

    private val imageObserver = Observer<String> {
        Glide.with(requireContext())
            .load(it)
            .placeholder(R.drawable.user)
            .into(binding.imgUser)
    }

    private val changeNameObserver = Observer<Unit> {
        val dialog = ChangeNameDialog(requireContext(), viewModel.nameLiveData.value!!)
        dialog.show()
        dialog.setChangeListener {
            viewModel.setName(it)
        }
    }

    private val backListener = Observer<Unit> {
        findNavController().navigateUp()
    }

    private val profileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                viewModel.setImage(uri.toString())
                mProfileUri = uri
            }


        }
    private val changeImageObserver = Observer<Unit> {
        ImagePicker.with(requireActivity())
            .crop()
            .cropOval()
            .maxResultSize(512, 512, true)
            .provider(ImageProvider.BOTH)
            .createIntentFromDialog {
                profileLauncher.launch(it)
            }


    }
    private val contactObserver = Observer<Unit> {
        val intent =
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/in/begzod-suyunov/?originalSubdomain=uz")
            )
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
        intent.putExtra(Intent.EXTRA_TEXT, "")
        startActivity(Intent.createChooser(intent, "Choose an Linkedin client :"))
    }
    private val supportObserver = Observer<Unit> {
        Constants.goToPlayMarket(activity as MainActivity)
    }}