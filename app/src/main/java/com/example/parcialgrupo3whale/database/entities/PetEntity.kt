package com.example.parcialgrupo3whale.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.parcialgrupo3whale.enums.Location

@Entity(tableName = "pets")
data class PetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val petName: String = "",
    val petAge: String = "",
    val petWeigh: String = "",
    val petDescription: String = "",
    val gender: Boolean,
    val location: Location,
    val owner: String = "",
    val images: String = "",
    val breed: String = "",
    val subBreed: String = ""
)
