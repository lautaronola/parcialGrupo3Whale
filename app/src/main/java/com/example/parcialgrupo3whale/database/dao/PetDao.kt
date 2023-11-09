package com.example.parcialgrupo3whale.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.parcialgrupo3whale.database.entities.PetEntity

interface PetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: PetEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPets(pets: List<PetEntity>)

    @Update
    suspend fun updatePet(pet: PetEntity?)

    @Delete
    suspend fun deletePet(pet: PetEntity?)

    @Query("SELECT * FROM pets")
    suspend fun getAllPets(): List<PetEntity?>

    @Query("SELECT * FROM pets WHERE id = :id")
    suspend fun loadPetById(id: Int): PetEntity?
}