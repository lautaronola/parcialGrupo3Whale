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
import com.example.parcialgrupo3whale.adapters.PetsListAdapter
import com.example.parcialgrupo3whale.entities.PetEntity
import com.example.parcialgrupo3whale.listener.OnDetailFragmentClickListener
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment(), OnDetailFragmentClickListener {
    private lateinit var view : View
    private lateinit var recyclerPets : RecyclerView

    private var pets : MutableList<PetEntity> = ArrayList()
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var petsListAdapter : PetsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()

        for(i in 1..6){
            pets.add(PetEntity("Luna", "10", "15", "", false, "CABA", "Lautaro", "calle", ""))
            pets.add(PetEntity("Tatu", "12", "20", "", true, "CABA", "Mateo", "pitbull", ""))
            pets.add(PetEntity("Buddy", "8", "10", "", true, "CABA", "Juan", "golden", ""))
            pets.add(PetEntity("Roma", "5", "11", "", false, "CABA", "Ariel", "chihuahua", ""))
            pets.add(PetEntity("Cuqui", "2", "14", "", false, "CABA", "Ursula", "calle", ""))
            pets.add(PetEntity("Paul", "3", "12", "", true, "CABA", "Matias", "golden", ""))
        }

        requireActivity()

        recyclerPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        petsListAdapter = PetsListAdapter(pets, this)

        recyclerPets.layoutManager = linearLayoutManager
        recyclerPets.adapter = petsListAdapter

    }

   override fun onViewItemDetail(pet: PetEntity){
      val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(pet)
       this.findNavController().navigate(action)
       Snackbar.make(view, pet.toString(), Snackbar.LENGTH_SHORT).show()
   }



}