package com.example.parcialgrupo3whale.activities

import android.content.Intent
import android.os.Handler
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parcialgrupo3whale.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    companion object {
        private const val SPLASH_TIME_OUT:Long = 3000 // 1,5 seconds
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler().postDelayed({
                navigateToWelcomeActivity()
        }, SPLASH_TIME_OUT)

    }

    private fun navigateToWelcomeActivity() {
        val welcomeActivityIntent = Intent(this, WelcomeActivity::class.java)
        startActivity(welcomeActivityIntent)
        finish()
    }
}