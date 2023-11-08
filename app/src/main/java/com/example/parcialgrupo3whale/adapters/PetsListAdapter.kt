package com.example.parcialgrupo3whale.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.entities.PetEntity
import com.example.parcialgrupo3whale.holders.PetHolder
import com.example.parcialgrupo3whale.listener.OnDetailFragmentClickListener

class PetsListAdapter(
    private val petsList : MutableList<PetEntity>,
    private val onItemClickListener: OnDetailFragmentClickListener
) : RecyclerView.Adapter<PetHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetHolder {
        //parent = recyclerView, inflo la vista
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pets, parent, false)

        // le paso la vista al holder
        return (PetHolder(view))
    }

    override fun getItemCount(): Int {
        return petsList.size
    }

    // bindea/carga de datos a la vista al holder tantas veces como la cantidad de items en la lista
    override fun onBindViewHolder(holder: PetHolder, position: Int) {
        val pet = petsList[position]

        holder.getCardLayout().setOnClickListener{
            onItemClickListener.onViewItemDetail(pet)
        }
    }
}