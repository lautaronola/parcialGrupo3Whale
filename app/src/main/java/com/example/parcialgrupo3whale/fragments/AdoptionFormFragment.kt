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
import androidx.navigation.fragment.findNavController
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.database.dao.WhaleDatabase
import com.example.parcialgrupo3whale.database.entities.PetEntity
import com.example.parcialgrupo3whale.enums.Location
import com.google.android.material.snackbar.Snackbar

class AdoptionFormFragment : Fragment() {

    private lateinit var addButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var weighEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var locationEditText: EditText
    private lateinit var breedEditText: EditText
    private lateinit var subBreedEditText: EditText
//    private lateinit var locationAutoCompleteTextView: AutoCompleteTextView

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
        locationEditText = view.findViewById(R.id.textEditLocationPet)
        breedEditText = view.findViewById(R.id.textEditBreedPet)
        subBreedEditText = view.findViewById(R.id.textEditSubBreedPet)
        addButton = view.findViewById(R.id.BtnAdoptionAdd)
//        locationAutoCompleteTextView = view.findViewById(R.id.autocompleteLocation)

        // Set up click listener for the add button
        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString()
            val weigh = weighEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val selectedRadioButtonId = genderRadioGroup.checkedRadioButtonId
            val gender: Boolean = selectedRadioButtonId == R.id.radioButtonFemale
//            val locationEnum = locationAutoCompleteTextView.text.toString()
            val breed = breedEditText.text.toString()
            val subBreed = subBreedEditText.text.toString()
            val owner = arguments?.getString("userName").toString()

            if (name.isNullOrEmpty() || age.isNullOrEmpty() || weigh.isNullOrEmpty() || description.isNullOrEmpty() || breed.isNullOrEmpty() || subBreed.isNullOrEmpty() || owner.isNullOrEmpty()) {
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
                location = Location.BUENOS_AIRES, // Location hardcodeada. Esperaba location = Location.valueOf(locationEnum),
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
}