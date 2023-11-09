package com.example.parcialgrupo3whale.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBindings
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
import com.example.parcialgrupo3whale.R
//import android.widget.RadioGroup


class AdoptionFormFragment : Fragment() {

    val locations =
        listOf("Buenos Aires","Cordoba","Entre Rios"
            ,"Corrientes","Misiones","Formosa","Chaco","Santa fe",
            "Santiago del estero","Tucumán","Salta","Jujuy","Catamarca","La Rioja",
            "San Juan", "San Luis","Mendoza","La Pampa","Neuquen","Rio negro",
            "Chubut","Santa Cruz","Tierra del Fuego").toList()

    val race = listOf("Generica","Golden","Chihuahua","Bulldog","Beagle","Otros").toList()
    val Subrace = listOf("Generica","Golden","Chihuahua","Bulldog","Beagle","Otros").toList()


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
        val namePet =
    }
}