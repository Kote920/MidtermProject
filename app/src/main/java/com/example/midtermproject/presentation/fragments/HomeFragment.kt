package com.example.midtermproject.presentation.fragments

import android.os.Bundle
import android.provider.CalendarContract.Reminders
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.midtermproject.R
import com.example.midtermproject.databinding.FragmentHomeBinding
import com.example.midtermproject.presentation.BaseFragment
import com.example.midtermproject.presentation.viewModels.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {



    private val viewModel: HomeViewModel by viewModels()

    override fun setUp() {
        replaceFragment(RemindersFragment())
        setBottomNavigationBarSelectListener()
    }

    private fun setBottomNavigationBarSelectListener() {
        binding.apply {
            bottomNavigationView.selectedItemId = R.id.nav_reminders
            bottomNavigationView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_reminders -> {
                        replaceFragment(RemindersFragment())
                        true
                    }

                    R.id.nav_detector -> {
                        replaceFragment(DetectorFragment())
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.homePageNavigationHostContainer, fragment)
            .commit()
    }

    override fun listeners() {

        binding.btnMenu.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), binding.btnMenu)

            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)


            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.changePassword -> {
                        replaceFragment(ChangePasswordFragment())
                        true
                    }

                    R.id.logOut -> {
                        viewModel.killSession()
                       openLogIn()
                        true
                    }

                    else -> false
                }
            }

            popupMenu.show()
        }

    }

    private fun openLogIn() {
        val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
        findNavController().navigate(action)
    }
    private fun openChangePassword() {
        val action = HomeFragmentDirections.actionHomeFragmentToChangePasswordFragment()
        findNavController().navigate(action)
    }
}


