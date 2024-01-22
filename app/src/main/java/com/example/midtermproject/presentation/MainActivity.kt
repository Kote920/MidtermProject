package com.example.midtermproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.example.midtermproject.R
import com.example.midtermproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var isSplashFragmentVisible = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isSplashFragmentVisible) {
                    // Do nothing or handle your desired behavior when the SplashFragment is visible
                } else {
                    // Allow the default back button behavior for the LoginFragment
                    isEnabled = false // Disable this callback
                  // Allow the default back button behavior
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, callback)


    }

    fun notifySplashFragmentNotVisible() {
        isSplashFragmentVisible = false
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.profileFragment -> {
//                findNavController(R.id.fragmentContainer).navigate(R.id.profileFragment)
//                true
//            }
//            R.id.remindersFragment -> {
//                findNavController(R.id.fragmentContainer).navigate(R.id.remindersFragment)
//                true
//            }
//            R.id.detectorFragment -> {
//                findNavController(R.id.fragmentContainer).navigate(R.id.detectorFragment)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}