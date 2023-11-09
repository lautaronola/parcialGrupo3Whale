package com.example.parcialgrupo3whale.fragments

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
import android.widget.Toast
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.database.dao.WhaleDatabase
import com.example.parcialgrupo3whale.database.entities.PetEntity
import com.example.parcialgrupo3whale.enums.Location

class AdoptionFormFragment : Fragment() {

    private lateinit var addButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var weighEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var locationAutoCompleteTextView: AutoCompleteTextView
    private lateinit var breedAutoCompleteTextView: AutoCompleteTextView
    private lateinit var subBreedAutoCompleteTextView: AutoCompleteTextView

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
        weighEditText = view.findViewById(R.id.textEditWeighPet)
        descriptionEditText = view.findViewById(R.id.textEditDescriptionPet)
        genderRadioGroup = view.findViewById(R.id.radioGroupGenres)
        locationAutoCompleteTextView = view.findViewById(R.id.autocompleteLocation)
        breedAutoCompleteTextView = view.findViewById(R.id.autocompleteBreed)
        subBreedAutoCompleteTextView = view.findViewById(R.id.autocompleteSubBreed)
        addButton = view.findViewById(R.id.BtnAdoptionAdd)

        // Set up click listener for the add button
        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString()
            val weigh = weighEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val selectedRadioButtonId = genderRadioGroup.checkedRadioButtonId
            val gender: Boolean = selectedRadioButtonId == R.id.radioButtonFemale
            val location = locationAutoCompleteTextView.text.toString()
            val breed = breedAutoCompleteTextView.text.toString()
            val subBreed = subBreedAutoCompleteTextView.text.toString()
            val owner = arguments?.getString("userName").toString()

            if (name.isNullOrEmpty() || age.isNullOrEmpty() || weigh.isNullOrEmpty() || description.isNullOrEmpty() || location.isNullOrEmpty() || breed.isNullOrEmpty() || subBreed.isNullOrEmpty() || owner.isNullOrEmpty()) {
                Toast.makeText(context, "Formulario Incompleto! Faltan campos", Toast.LENGTH_SHORT).show()
            } else {
            // Crear una instancia de PetEntity
            val pet = PetEntity(
                id = 0,
                petName= name,
                petAge = age,
                petWeigh = weigh,
                petDescription = description,
                gender = gender,
                location = Location.valueOf(location),
                owner = owner,
                breed = breed,
                subBreed = subBreed
            )

            // Loggear la información de la mascota
            Log.d("PetInfo", pet.toString())

            // Agregar la mascota a la lista
            val db = WhaleDatabase.getWhaleDatabase(requireContext())
            db?.petDao()?.insertPet(pet)
            }
        }
        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setDataAdoptionPet()
//    }
//
//    private fun setDataAdoptionPet() {
//        //binding
//    }
}