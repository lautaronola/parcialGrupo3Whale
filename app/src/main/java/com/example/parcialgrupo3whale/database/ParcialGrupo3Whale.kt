package com.example.parcialgrupo3whale.database

import android.app.Application
import androidx.room.Room
import com.example.parcialgrupo3whale.database.dao.WhaleDatabase

class ParcialGrupo3Whale : Application() {

    companion object {
        lateinit var database: WhaleDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            WhaleDatabase::class.java, "whale-database"
        ).build()
    }
}