package com.example.parcialgrupo3whale.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.database.dao.WhaleDatabase
import com.example.parcialgrupo3whale.database.entities.PetEntity
import com.example.parcialgrupo3whale.enums.Location
import com.example.parcialgrupo3whale.gateway.model.PetBreedsAPIResponse
import com.example.parcialgrupo3whale.gateway.model.PetSubBreedAPIResponse
import com.example.parcialgrupo3whale.gateway.service.PetAPIService
import com.example.parcialgrupo3whale.gateway.service.ServicePetApiBuilder
import com.example.parcialgrupo3whale.listener.OnDetailFragmentClickListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdoptionFormFragment : Fragment(), OnDetailFragmentClickListener {
    private lateinit var view : View
    private lateinit var addButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var weighEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var locationEditText: EditText
    private var petApiService : PetAPIService = ServicePetApiBuilder.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_adoption_form, container, false)

        // Initialize your view references
        nameEditText = view.findViewById(R.id.textEditNamePet)
        ageEditText = view.findViewById(R.id.textEditAgePet)
        weighEditText = view.findViewById(R.id.textEditWeighPet)
        descriptionEditText = view.findViewById(R.id.textEditDescriptionPet)
        genderRadioGroup = view.findViewById(R.id.radioGroupGenres)
        locationEditText = view.findViewById(R.id.textEditLocationPet)
        val autoCompleteBreedPet: MaterialAutoCompleteTextView = view.findViewById(R.id.autoCompleteBreedPet)
        val autoCompleteSubBreedPet: MaterialAutoCompleteTextView = view.findViewById(R.id.autoCompleteSubBreedPet)
        addButton = view.findViewById(R.id.BtnAdoptionAdd)
//        locationAutoCompleteTextView = view.findViewById(R.id.autocompleteLocation)
        var subBreedsList: MutableList<String>? = null
        var breedsList: MutableList<String>? = null

        var breedAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, listOf())
        autoCompleteBreedPet.setAdapter(breedAdapter)

        var subBreedAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, listOf())
        autoCompleteSubBreedPet.setAdapter(subBreedAdapter)


        petApiService.getBreedsList().enqueue(object : Callback<PetBreedsAPIResponse> {
            override fun onResponse(call: Call<PetBreedsAPIResponse>, response: Response<PetBreedsAPIResponse>) {
                if (response.isSuccessful) {
                    breedsList = response.body()?.message?.keys?.toMutableList() ?: mutableListOf()
                    breedAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line,
                        breedsList!!
                    )
                    autoCompleteBreedPet.setAdapter(breedAdapter)
                }
            }

            override fun onFailure(call: Call<PetBreedsAPIResponse>, t: Throwable) {
                // Muestra un Toast con un mensaje de error
                Toast.makeText(context, "Error al obtener la lista de razas de mascotas", Toast.LENGTH_SHORT).show()

                // Imprimir el mensaje de error en el Logcat
                Log.e("PetAPIService", "Error al obtener la lista de razas de mascotas", t)
            }
        })

        autoCompleteBreedPet.setOnItemClickListener { _, _, position, _ ->
            // Obtiene la raza seleccionada del adaptador
            val selectedBreed = breedAdapter.getItem(position)

            // Utiliza el servicio para obtener las subrazas asociadas con la raza seleccionada
            if (selectedBreed != null) {
                petApiService.getSubBreed(selectedBreed ?: "")
                    .enqueue(object : Callback<PetSubBreedAPIResponse> {
                        override fun onResponse(
                            call: Call<PetSubBreedAPIResponse>,
                            response: Response<PetSubBreedAPIResponse>
                        ) {
                            if (response.isSuccessful) {
                                // Procesa la respuesta y actualiza el adaptador de subrazas
                                subBreedsList = response.body()?.message?.toMutableList() ?: mutableListOf()
                                subBreedAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line,
                                    subBreedsList!!
                                )
                                autoCompleteSubBreedPet.setAdapter(subBreedAdapter)
                            } else {
                                Toast.makeText(context, "Error al obtener las subrazas de mascotas", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<PetSubBreedAPIResponse>, t: Throwable) {
                            // Maneja el error aquí
                            Toast.makeText(context, "Error al obtener las subrazas de mascotas", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
            // Limpia el campo de subraza cuando se selecciona una nueva raza
            autoCompleteSubBreedPet.text.clear()
        }

        // Set up click listener for the add button
        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString()
            val weigh = weighEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val selectedRadioButtonId = genderRadioGroup.checkedRadioButtonId
            val gender: Boolean = selectedRadioButtonId == R.id.radioButtonFemale
//            val locationEnum = locationAutoCompleteTextView.text.toString()
            val breed = autoCompleteBreedPet.text.toString()
            val subBreed = autoCompleteSubBreedPet.text.toString()
            val owner = arguments?.getString("userName").toString()
            val breedListString = breedsList.toString()

            if (name.isNullOrEmpty() || age.isNullOrEmpty() || weigh.isNullOrEmpty() || description.isNullOrEmpty() || breed.isNullOrEmpty() || owner.isNullOrEmpty() || subBreed.isNullOrEmpty()) {
                Toast.makeText(context, "Formulario Incompleto! Faltan campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Verificar si la raza seleccionada está en la lista de razas
            if (!breedListString.contains(breed)) {
                Toast.makeText(context, "Raza no válida", Toast.LENGTH_SHORT).show()
                autoCompleteBreedPet.text.clear() // Limpiar el campo si la raza no es válida
                return@setOnClickListener
            }

            // Crear una instancia de PetEntity
            val pet = PetEntity(
                petName= name,
                petAge = age,
                petWeigh = weigh,
                petDescription = description,
                gender = gender,
                location = Location.BUENOS_AIRES, // Location hardcodeada. Esperaba location = Location.valueOf(locationEnum),
                owner = owner,
                breed = breed,
                subBreed = subBreed,
                images = "https://images.dog.ceo/breeds/pomeranian/n02112018_863.jpg"
            )

            // Loggear la información de la mascota
            Log.d("PetInfo", pet.toString())
            // Ej de lo que llega: PetEntity(id=0, petName=Kim, petAge=6, petWeigh=14, petDescription=Perro mediano tranquilo, gender=true, location=BUENOS_AIRES, owner=Juanchi, images=, breed=springer, subBreed=english, isFavorite=false)

                // Agregar la mascota a la lista
            val db = WhaleDatabase.getWhaleDatabase(requireContext())
            db?.petDao()?.insertPet(pet)
            onViewItemDetail(pet)
        }
        return view
    }

    override fun onViewItemDetail(pet: PetEntity){
        val action = AdoptionFormFragmentDirections.actionAdoptionFormFragmentToDetailFragment(pet)
        this.findNavController().navigate(action)
        Snackbar.make(view, pet.toString(), Snackbar.LENGTH_SHORT).show()
    }

    override fun onFavoriteButtonClick(pet: PetEntity) {
        TODO("Not yet implemented")
    }
}