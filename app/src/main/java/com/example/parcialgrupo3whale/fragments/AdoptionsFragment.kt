package com.example.parcialgrupo3whale.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.adapters.PetsListFavAdapter
import com.example.parcialgrupo3whale.database.dao.WhaleDatabase
import com.example.parcialgrupo3whale.database.entities.PetEntity
import com.example.parcialgrupo3whale.listener.OnDetailFragmentClickListener
import com.google.android.material.snackbar.Snackbar

class AdoptionsFragment : Fragment(), OnDetailFragmentClickListener {
    private lateinit var view : View
    private lateinit var recyclerAdoptionsPets : RecyclerView
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
        view = inflater.inflate(R.layout.fragment_adoptions, container, false)
        recyclerAdoptionsPets = view.findViewById(R.id.recyclerAdoptionsPets)
        return view
    }

    override fun onStart() {
        super.onStart()

        requireActivity()

        recyclerAdoptionsPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)

        val db = WhaleDatabase.getWhaleDatabase(requireContext())
        if (db != null) {
            pets = db.petDao().getAdoptedPets()
        }

        petsListFavAdapter = PetsListFavAdapter(pets, this, db)
        recyclerAdoptionsPets.layoutManager = linearLayoutManager
        recyclerAdoptionsPets.adapter = petsListFavAdapter
    }

    override fun onViewItemDetail(pet: PetEntity){
        val action = AdoptionsFragmentDirections.actionAdoptionsFragmentToDetailFragment(pet)
        this.findNavController().navigate(action)
        Snackbar.make(view, pet.toString(), Snackbar.LENGTH_SHORT).show()
    }

    override fun onFavoriteButtonClick(pet: PetEntity) {
        TODO("Not yet implemented")
    }
}