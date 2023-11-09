package com.example.parcialgrupo3whale.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.parcialgrupo3whale.database.entities.PetEntity

@Database(entities = [PetEntity::class], version = 1, exportSchema = false)
abstract class WhaleDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
}