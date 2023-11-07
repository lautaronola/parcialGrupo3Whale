package com.example.parcialgrupo3whale.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
import com.example.parcialgrupo3whale.R
//import android.widget.RadioGroup

class AdoptionFormFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adoption_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataAdoptionPet()
    }

    private fun setDataAdoptionPet() {
        //binding
    }
}