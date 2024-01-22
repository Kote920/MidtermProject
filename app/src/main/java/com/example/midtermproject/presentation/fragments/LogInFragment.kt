package com.example.midtermproject.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.databinding.FragmentLogInBinding
import com.example.midtermproject.presentation.BaseFragment
import com.example.midtermproject.presentation.viewModels.LogInFragmentNavigationEvent
import com.example.midtermproject.presentation.viewModels.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val viewModel: LogInViewModel by viewModels()


    override fun setUp() {
        binding.btnLogIn.isEnabled = true
    }

    override fun listeners() {
        binding.btnLogIn.setOnClickListener {
            viewModel.logIn(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            bindObserves()
        }

        binding.btnRegister.setOnClickListener {
            openRegister()
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.logInFlow.collect {
                    when (it) {
                        is Resource.Loading -> {
                            binding.pbLogIn.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            val activeUser = it.responseData

                            binding.pbLogIn.visibility = View.GONE
                            Toast.makeText(requireContext(), "Log In  Success", Toast.LENGTH_SHORT).show()
                            viewModel.saveToken(activeUser.user!!.email.toString(), binding.cbRememberMe.isChecked)
                            viewModel.successFlow.collect(){
                                when(it){
                                    is LogInFragmentNavigationEvent.NavigationToHome ->{
                                        binding.pbLogIn.visibility = View.GONE
                                        binding.btnLogIn.isEnabled = false
                                        openHome()}
                                }

                            }

                        }

                        is Resource.Failed -> {
                            binding.pbLogIn.visibility = View.GONE
                            val errorMessage = it.message
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                                .show()

                        }
                    }
                }
            }
        }
    }

    private fun openHome() {
        val action = LogInFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun openRegister() {
        val action = LogInFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
    }

}