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
import com.example.parcialgrupo3whale.adapters.PetsListHomeAdapter
import com.example.parcialgrupo3whale.database.dao.WhaleDatabase
import com.example.parcialgrupo3whale.database.entities.PetEntity
import com.example.parcialgrupo3whale.listener.OnDetailFragmentClickListener
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment(), OnDetailFragmentClickListener {
    private lateinit var view : View
    private lateinit var recyclerPets : RecyclerView
    private lateinit var pets: List<PetEntity>
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var petsListHomeAdapter : PetsListHomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerPets = view.findViewById(R.id.recyclerPets)
        return view
    }

    override fun onStart() {
        super.onStart()

        requireActivity()

        recyclerPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)

        val db = WhaleDatabase.getWhaleDatabase(requireContext())
        if (db != null) {
            pets = db.petDao().getAllPets()
        }

        petsListHomeAdapter = PetsListHomeAdapter(pets, this, db)
        recyclerPets.layoutManager = linearLayoutManager
        recyclerPets.adapter = petsListHomeAdapter
    }

   override fun onViewItemDetail(pet: PetEntity){
      val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(pet)
       this.findNavController().navigate(action)
       Snackbar.make(view, pet.toString(), Snackbar.LENGTH_SHORT).show()
   }
}