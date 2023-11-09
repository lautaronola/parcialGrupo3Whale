package com.example.parcialgrupo3whale.gateway.service

import com.example.parcialgrupo3whale.gateway.model.PetBreedsAPIResponse
import com.example.parcialgrupo3whale.gateway.model.PetRandomImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Response

interface PetAPIService {
    @GET("/api/breeds/list/all")
    fun getBreedsList() : Call<PetBreedsAPIResponse>

    @GET("api/breeds/image/random")
    fun getRandomImage() : Call<PetRandomImageResponse>

}