package com.example.midtermproject.presentation.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.midtermproject.databinding.FragmentSplashBinding
import com.example.midtermproject.presentation.BaseFragment
import com.example.midtermproject.presentation.viewModels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel: SplashViewModel by viewModels()


    override fun setUp() {

        Glide.with(this)
            .load("https://cdn.pixabay.com/animation/2023/10/08/03/19/03-19-26-213_512.gif")
            .into(binding.gifImageView)
        viewModel.readSession()
        bindObserves()

    }

    override fun listeners() {

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.sessionFlow.collect{remember ->

                    delay(2000)
                    openSpecificFragment(remember)


                }
            }
        }
    }

    private fun openLoginFragment(){
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())

    }
    private fun openHomeFragment(){
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
    }

    private fun openSpecificFragment( remember: Boolean,){
        if (remember){

            openHomeFragment()
        }else{
            openLoginFragment()
        }

    }

}
