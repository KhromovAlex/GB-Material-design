package com.example.gbmaterialdesign.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.gbmaterialdesign.R
import com.example.gbmaterialdesign.databinding.MainNavBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {
    private var _binding: MainNavBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainNavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    navController.navigate(R.id.nav_home)
                    this@BottomNavigationDrawerFragment.dismiss()
                    return@setNavigationItemSelectedListener true
                }
                R.id.action_settings -> {
                    navController.navigate(R.id.nav_settings)
                    this@BottomNavigationDrawerFragment.dismiss()
                    return@setNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}
