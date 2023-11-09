package com.example.parcialgrupo3whale.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.database.dao.PetDao_Impl
import com.example.parcialgrupo3whale.databinding.FragmentAdoptionFormBinding
import com.example.parcialgrupo3whale.entities.PetEntity

//import android.widget.RadioGroup

class AdoptionFormFragment : Fragment() {

    private lateinit var addButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var locationAutoCompleteTextView: AutoCompleteTextView
    private lateinit var raceAutoCompleteTextView: AutoCompleteTextView
    private lateinit var subraceAutoCompleteTextView: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_adoption_form, container, false)

        // Initialize your view references
        nameEditText = view.findViewById(R.id.textEditNamePet)
        ageEditText = view.findViewById(R.id.textEditAgePet)
        weightEditText = view.findViewById(R.id.textEditWeightPet)
        descriptionEditText = view.findViewById(R.id.textEditDescriptionPet)
        genderRadioGroup = view.findViewById(R.id.radioGroupGenres)
        locationAutoCompleteTextView = view.findViewById(R.id.autocompleteLocation)
        raceAutoCompleteTextView = view.findViewById(R.id.autocompleteRace)
        subraceAutoCompleteTextView = view.findViewById(R.id.autocompleteSubrace)
        addButton = view.findViewById(R.id.BtnAdoptionAdd)

        // Set up click listener for the add button
        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString()
            val weight = weightEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val gender = genderRadioGroup.checkedRadioButtonId == R.id.radioButtonFemale
            val location = locationAutoCompleteTextView.text.toString()
            val race = raceAutoCompleteTextView.text.toString()
            val subrace = subraceAutoCompleteTextView.text.toString()
            val owner = arguments?.getString("userName").toString()

            // Crear una instancia de PetEntity
            val pet = PetEntity(name, age, weight, description, gender, location, owner, race, subrace)

            // Loggear la información de la mascota
            Log.d("PetInfo", pet.toString())

            // Agregar la mascota a la lista
            db.petDao().insertPet(pet)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataAdoptionPet()
    }

    private fun setDataAdoptionPet() {
        //binding
    }
}