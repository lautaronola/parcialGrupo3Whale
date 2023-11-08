package com.example.parcialgrupo3whale.gateway.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServicePetApiBuilder {

    private const val BASE_URL = "https://dog.ceo/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun create(): PetAPIService{
        return retrofit.create(PetAPIService::class.java)
    }
}