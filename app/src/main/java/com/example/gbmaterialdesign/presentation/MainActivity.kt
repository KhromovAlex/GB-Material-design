package com.example.gbmaterialdesign.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gbmaterialdesign.R
import com.example.gbmaterialdesign.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setSelectedTheme()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    private fun setSelectedTheme() {
        val preferences = getPreferences(MODE_PRIVATE)
        val theme = preferences.getInt("theme", R.style.Theme_GBMaterialDesign)
        setTheme(theme)
    }
}
