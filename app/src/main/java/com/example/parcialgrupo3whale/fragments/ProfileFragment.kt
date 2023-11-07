package com.example.parcialgrupo3whale.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.parcialgrupo3whale.R

class ProfileFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val argumentValue = arguments?.getString("userName")
        val textView: TextView = view.findViewById(R.id.nameProfile)

        textView.text = argumentValue

        return view
    }
}