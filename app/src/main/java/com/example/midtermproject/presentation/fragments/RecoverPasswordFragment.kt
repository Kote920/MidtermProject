package com.example.midtermproject.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.midtermproject.R
import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.databinding.FragmentRecoverPasswordBinding
import com.example.midtermproject.presentation.base.BaseFragment
import com.example.midtermproject.presentation.viewModels.RecoverPasswordViewModel
import kotlinx.coroutines.launch

class RecoverPasswordFragment :
    BaseFragment<FragmentRecoverPasswordBinding>(FragmentRecoverPasswordBinding::inflate) {

    private val viewModel: RecoverPasswordViewModel by viewModels()

    override fun setUp() {

    }

    override fun listeners() {
        binding.btnRecoverPassword.setOnClickListener {

        }

//    override fun bindObserves() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.recoverPasswordFlow.collect {
//                    when (it) {
//                        is Resource.Loading -> {
//                            binding.pbRecoverPassword.visibility = View.VISIBLE
//                        }
//
//                        is Resource.Success -> {
////                            val registeredUser = it.responseData
//                            binding.pbRecoverPassword.visibility = View.GONE
//                            Toast.makeText(requireContext(), "Recovery link sent on email!", Toast.LENGTH_SHORT).show()
//
//
//                        }
//
//                        is Resource.Failed -> {
//                            binding.pbRecoverPassword.visibility = View.GONE
//                            val errorMessage = it.message
//                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
//                                .show()
//
//                        }
//                    }
//                }
//            }
//        }
//    }


    }
}