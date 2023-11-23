package com.example.parcialgrupo3whale.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.parcialgrupo3whale.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWelcomeBinding
    private var userName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!userName.isNullOrEmpty()){
            navigateToMainActivity()
        } else {
            binding = ActivityWelcomeBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.btnEnterApp.setOnClickListener {
                userName = binding.editTextName.text.toString()
                Toast.makeText(this, "Bienvenido $userName!", Toast.LENGTH_SHORT).show()
                binding.btnEnterApp.isEnabled = false
                binding.btnEnterApp.text = "Iniciando sesion..."
                navigateToMainActivity()
            }
        }
    }

    private fun navigateToMainActivity() {
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        mainActivityIntent.putExtra("userName", userName)
        startActivity(mainActivityIntent)
        finish()
    }

}