package com.example.parcialgrupo3whale.database.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class PetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val petName: String,
    val petAge: String,
    val petWeigh: String,
    val petDescription: String,
    val gender: Boolean,
    val location: String,
    val owner: String,
    val images: List<String>,
    val breeds: String,
    val subBreeds: String
)
