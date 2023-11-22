package com.example.parcialgrupo3whale.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.adapters.PetsListFavAdapter
import com.example.parcialgrupo3whale.database.dao.WhaleDatabase
import com.example.parcialgrupo3whale.database.entities.PetEntity
import com.example.parcialgrupo3whale.listener.OnDetailFragmentClickListener
import com.google.android.material.snackbar.Snackbar

class FavouriteFragment : Fragment(), OnDetailFragmentClickListener{
    private lateinit var view : View
    private lateinit var recyclerFavPets : RecyclerView
    private lateinit var pets: List<PetEntity>
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var petsListFavAdapter : PetsListFavAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favourite, container, false)
        recyclerFavPets = view.findViewById(R.id.recyclerFavPets)
        return view
    }

    override fun onStart() {
        super.onStart()

        requireActivity()

        recyclerFavPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)

        val db = WhaleDatabase.getWhaleDatabase(requireContext())
        if (db != null) {
            pets = db.petDao().getAllFavoritePets()
        }

        petsListFavAdapter = PetsListFavAdapter(pets, this, db)
        recyclerFavPets.layoutManager = linearLayoutManager
        recyclerFavPets.adapter = petsListFavAdapter
    }

    override fun onViewItemDetail(pet: PetEntity){
        val action = FavouriteFragmentDirections.actionFavouriteFragmentToDetailFragment(pet)
        this.findNavController().navigate(action)
        Snackbar.make(view, pet.toString(), Snackbar.LENGTH_SHORT).show()
    }

    override fun onFavoriteButtonClick(pet: PetEntity) {
        pet.isFavorite = false
        val db = WhaleDatabase.getWhaleDatabase(requireContext())
        db?.petDao()?.updatePet(pet)

        Toast.makeText(context, "${pet.petName} eliminado de favoritos", Toast.LENGTH_SHORT).show()

        val newPetsList = db?.petDao()?.getAllFavoritePets() ?: emptyList()

        petsListFavAdapter.updatePetsList(newPetsList)
    }
}