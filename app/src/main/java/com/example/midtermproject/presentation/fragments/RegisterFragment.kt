package com.example.midtermproject.presentation.fragments

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.databinding.FragmentRegisterBinding
import com.example.midtermproject.presentation.base.BaseFragment
import com.example.midtermproject.presentation.viewModels.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()
    override fun setUp() {
        binding.btnRegister.isEnabled = true
    }

    override fun listeners() {
        binding.btnRegister.setOnClickListener{
            viewModel.register(binding.etEmail.text.toString(), binding.etPassword.text.toString(), binding.etRepeatPassword.text.toString())
            bindObserves()
        }

        binding.btnLogIn.setOnClickListener {
            openLogIn()
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerFlow.collect {
                    when (it) {
                        is Resource.Loading -> {
                            binding.pbRegister.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            val registeredUser = it.responseData
                            binding.pbRegister.visibility = View.GONE
                            Toast.makeText(requireContext(), "Registration Success", Toast.LENGTH_SHORT).show()
                            binding.btnRegister.isEnabled = false
                            openLogIn()


                        }

                        is Resource.Failed -> {
                            binding.pbRegister.visibility = View.GONE
                            val errorMessage = it.message
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                                .show()

                        }
                    }
                }
            }
        }
    }

    private fun openLogIn() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        findNavController().navigate(action)

    }

}