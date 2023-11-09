package com.example.parcialgrupo3whale.database.dao

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.parcialgrupo3whale.database.entities.PetEntity

@Database(entities = [PetEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WhaleDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao

    companion object {
        var INSTANCE : WhaleDatabase? = null

        fun getWhaleDatabase(context : Context) : WhaleDatabase? {
            if (INSTANCE == null) {
                synchronized(WhaleDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WhaleDatabase::class.java,
                        "petDB"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }

    fun destroyDataBase(){
        INSTANCE = null
    }
}