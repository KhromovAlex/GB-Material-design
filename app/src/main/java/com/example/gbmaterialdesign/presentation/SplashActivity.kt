package com.example.gbmaterialdesign.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.gbmaterialdesign.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.logo.alpha = .3f
        binding.logo.scaleX = .5f
        binding.logo.scaleY = .5f

        binding.logo.animate().alpha(1f)
            .setInterpolator(LinearInterpolator()).duration = 5000
        binding.logo.animate().scaleX(2f)
            .setInterpolator(LinearInterpolator()).duration = 5000
        binding.logo.animate().scaleY(2f)
            .setInterpolator(LinearInterpolator()).duration = 5000

        handler.postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
