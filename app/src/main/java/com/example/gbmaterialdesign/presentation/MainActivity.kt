package com.example.gbmaterialdesign.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.gbmaterialdesign.R
import com.example.gbmaterialdesign.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setSelectedTheme()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setNav()
    }

    private fun setSelectedTheme() {
        val preferences = getPreferences(MODE_PRIVATE)
        val theme = preferences.getInt("theme", R.style.Theme_GBMaterialDesign)
        setTheme(theme)
    }

    private fun setNav() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNav, navController)

        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    navController.navigate(R.id.nav_home)
                    true
                }
                R.id.action_astronomical_objects -> {
                    navController.navigate(R.id.nav_action_astronomical_objects)
                    true
                }
                R.id.action_settings -> {
                    navController.navigate(R.id.nav_settings)
                    true
                }
                R.id.action_task_list -> {
                    navController.navigate(R.id.nav_task_list)
                    true
                }
                else -> false
            }
        }
    }
}
