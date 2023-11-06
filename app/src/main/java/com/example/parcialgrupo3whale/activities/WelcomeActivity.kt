package com.example.parcialgrupo3whale.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parcialgrupo3whale.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWelcomeBinding
    private var userName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(userName != null){
            navigateToMainActivity()
        } else {
            binding = ActivityWelcomeBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.btnEnterApp.setOnClickListener {
                userName = binding.editTextName.text.toString()
                navigateToMainActivity()
            }
        }
    }

    private fun navigateToMainActivity() {
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        startActivity(mainActivityIntent)
        finish()
    }

}