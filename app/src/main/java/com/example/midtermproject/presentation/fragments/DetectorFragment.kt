package com.example.midtermproject.presentation.fragments

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.databinding.FragmentDetectorBinding
import com.example.midtermproject.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetectorFragment : BaseFragment<FragmentDetectorBinding>(FragmentDetectorBinding::inflate) {
//    private val viewModel: DetectorViewModel by viewModels()

    override fun setUp() {
//        viewModel.get()
        bindObserves()
    }

    override fun listeners() {
    }

//    override fun bindObserves() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.logInFlow.collect {
//                    when (it) {
//                        is Resource.Loading -> {
//
//                        }
//
//                        is Resource.Success -> {
//                            val registeredUser = it.responseData
//                            d("responseMedicine", registeredUser.toString())
//
//
//
//                        }
//
//                        is Resource.Failed -> {
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