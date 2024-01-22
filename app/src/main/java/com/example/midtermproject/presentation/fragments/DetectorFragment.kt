package com.example.midtermproject.presentation.fragments

import com.example.midtermproject.databinding.FragmentDetectorBinding
import com.example.midtermproject.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

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