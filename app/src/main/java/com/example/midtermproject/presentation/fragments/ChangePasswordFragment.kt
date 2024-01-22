package com.example.midtermproject.presentation.fragments

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.databinding.FragmentChangePasswordBinding
import com.example.midtermproject.presentation.base.BaseFragment
import com.example.midtermproject.presentation.viewModels.ChangePasswordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>(FragmentChangePasswordBinding::inflate) {

    private val viewModel: ChangePasswordViewModel by viewModels()

//    private val safeargs: HomeFragmentArgs by navArgs()

    override fun setUp() {
        binding.btnChangePassword.isActivated = false
    }

    override fun listeners() {
//
//        binding.btnChangePassword.setOnClickListener {
//            viewModel.changePassword(safeargs.email, binding.etCurrentPassword.text.toString(), binding.etNewPassword.text.toString())
//            bindObserves()
//        }

    }

    override fun bindObserves() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.changePasswordFlow.collect{
                    when(it){
                        is Resource.Loading -> {
                            binding.pbChangePassword.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            val res =  it.responseData
                            binding.pbChangePassword.visibility = View.GONE
                            Toast.makeText(requireContext(), "Successfuly changed", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Failed -> {
                            Toast.makeText(requireContext(), "Failed to change!!", Toast.LENGTH_SHORT).show()
                            binding.pbChangePassword.visibility = View.GONE
                        }
                    }
                }
            }
        }

    }


}