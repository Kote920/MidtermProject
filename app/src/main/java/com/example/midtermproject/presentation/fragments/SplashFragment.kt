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
            .load("https://media.giphy.com/media/suiMTS2HD7oZ0SB8Dw/giphy.gif")
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
                    viewModel.emailFlow.collect{
                        openSpecificFragment(remember, it)
                    }


                }
            }
        }
    }

    private fun openLoginFragment(){
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())

    }
    private fun openHomeFragment(email: String){
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment(email))
    }

    private fun openSpecificFragment( remember: Boolean, email:String){
        if (remember){

            openHomeFragment(email)
        }else{
            openLoginFragment()
        }

    }

}
