package com.example.parcialgrupo3whale.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.parcialgrupo3whale.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbar()
        setUpNavigation()
    }

    private fun setUpToolbar(){
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }

    private fun setUpNavigation(){

    }
}