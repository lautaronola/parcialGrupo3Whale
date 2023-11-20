package com.example.parcialgrupo3whale.listener

import com.example.parcialgrupo3whale.database.entities.PetEntity

interface OnDetailFragmentClickListener {
        fun onViewItemDetail(pet: PetEntity)
    abstract fun onFavoriteButtonClick(pet: PetEntity)
}