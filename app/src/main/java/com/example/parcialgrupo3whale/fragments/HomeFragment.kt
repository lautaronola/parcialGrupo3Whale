package com.example.parcialgrupo3whale.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.adapters.PetsListHomeAdapter
import com.example.parcialgrupo3whale.database.dao.WhaleDatabase
import com.example.parcialgrupo3whale.database.entities.PetEntity
import com.example.parcialgrupo3whale.listener.OnDetailFragmentClickListener
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment(), OnDetailFragmentClickListener {
    private lateinit var view : View
    private lateinit var buttonMoreFilters: TextView
    private lateinit var recyclerPets : RecyclerView
    private lateinit var pets: List<PetEntity>
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var petsListHomeAdapter : PetsListHomeAdapter
    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private lateinit var chipGroupFilters: ChipGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        buttonMoreFilters = view.findViewById(R.id.buttonMoreFilters)

        buttonMoreFilters.setOnClickListener {
            val popup = PopupMenu(requireContext(), buttonMoreFilters)
            popup.inflate(R.menu.more_filters)
            popup.setOnMenuItemClickListener {
                Toast.makeText(requireContext(), "Item: " + it.title, Toast.LENGTH_SHORT).show()
                true
            }
            popup.show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerPets = view.findViewById(R.id.recyclerPets)

        searchView = view.findViewById(R.id.searchBar)
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchForPets(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchForPets(it) }
                return true
            }
        })

        chipGroupFilters = view.findViewById(R.id.chipGroupFilters)
        setupChipGroupListener()

        return view
    }

    private fun initializeRecyclerView(petsList: List<PetEntity>) {
        recyclerPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        petsListHomeAdapter = PetsListHomeAdapter(petsList, this, WhaleDatabase.getWhaleDatabase(requireContext()))
        recyclerPets.layoutManager = linearLayoutManager
        recyclerPets.adapter = petsListHomeAdapter
    }

    override fun onStart() {
        super.onStart()

        requireActivity()

        recyclerPets.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)

        val db = WhaleDatabase.getWhaleDatabase(requireContext())
        pets = db?.petDao()?.getAllPets() ?: emptyList()

        petsListHomeAdapter = PetsListHomeAdapter(pets, this, db)
        recyclerPets.layoutManager = linearLayoutManager
        recyclerPets.adapter = petsListHomeAdapter
        initializeRecyclerView(pets)
    }

    private fun searchForPets(query: String) {
        // Filtra la lista de mascotas basada en la raza
        val filteredList = pets.filter { it.breed.contains(query, ignoreCase = true) }
        // Actualiza el adaptador con la lista filtrada
        petsListHomeAdapter.updatePetsList(filteredList)
    }

   override fun onViewItemDetail(pet: PetEntity){
      val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(pet)
       this.findNavController().navigate(action)
       Snackbar.make(view, pet.toString(), Snackbar.LENGTH_SHORT).show()
   }

    private fun setupChipGroupListener() {
        chipGroupFilters.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.chip_golden -> searchForPets("Golden")
                R.id.chip_salchicha -> searchForPets("Salchicha")
                R.id.chip_terrier -> searchForPets("Terrier")
                else -> displayAllPets()
            }
        }
    }

    private fun displayAllPets() {
        petsListHomeAdapter.updatePetsList(pets)
    }

}