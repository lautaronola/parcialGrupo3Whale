package com.example.parcialgrupo3whale.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.database.dao.PetDao
import com.example.parcialgrupo3whale.database.dao.WhaleDatabase
import com.example.parcialgrupo3whale.database.entities.PetEntity
import com.example.parcialgrupo3whale.gateway.service.PetAPIService
import com.example.parcialgrupo3whale.gateway.service.ServicePetApiBuilder
import com.example.parcialgrupo3whale.holders.PetHolder
import com.example.parcialgrupo3whale.listener.OnDetailFragmentClickListener

class PetsListHomeAdapter(
    private var petsList: List<PetEntity>,
    private val onItemClickListener: OnDetailFragmentClickListener,
    private val db: WhaleDatabase?
) : RecyclerView.Adapter<PetHolder>() {
    private var petDao: PetDao? = null
    private var petApiService: PetAPIService

    init {
        petDao = db?.petDao()
        petApiService = ServicePetApiBuilder.create()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetHolder {
        //parent = recyclerView, inflo la vista
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pets, parent, false)

        // le paso la vista al holder
        return (PetHolder(view))
    }

    override fun getItemCount(): Int {
        return petsList.size
    }

    override fun onBindViewHolder(holder: PetHolder, position: Int) {
        val pet = petsList[position]
        holder.bind(pet)

        holder.getCardLayout().setOnClickListener{
            onItemClickListener.onViewItemDetail(pet)
        }

        holder.getFavoriteImageView().setOnClickListener {
            onItemClickListener.onFavoriteButtonClick(pet)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePetsList(newPetsList: List<PetEntity>) {
        petsList = newPetsList
        notifyDataSetChanged()
    }
}