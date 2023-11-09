package com.example.parcialgrupo3whale.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.parcialgrupo3whale.database.entities.PetEntity

interface PetFavDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertPet(pet: PetEntity?)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertAllPets(pets: List<PetEntity>)

        @Update
        fun updatePet(pet: PetEntity?)

        @Delete
        fun deletePet(pet: PetEntity?)

        @Query("SELECT * FROM pets")
        fun getAllPets(): List<PetEntity>

        @Query("SELECT * FROM pets WHERE is_favorite = 1")
        suspend fun getAllFavoritePets(): List<PetEntity>

        @Query("SELECT * FROM pets WHERE id = :id")
        fun loadPetById(id: Int): PetEntity

}