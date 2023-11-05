package com.example.parcialgrupo3whale.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.parcialgrupo3whale.R

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        val imageView: ImageView = view.findViewById(R.id.imageView)
        val editTextName: EditText = view.findViewById(R.id.editTextName)
        val buttonEnter: Button = view.findViewById(R.id.buttonEnter)

        buttonEnter.setOnClickListener {
            val enteredName = editTextName.text.toString()
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment(enteredName)
            findNavController().navigate(action)
        }

        return view
    }
}