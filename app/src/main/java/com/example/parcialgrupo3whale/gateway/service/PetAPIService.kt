package com.example.parcialgrupo3whale.gateway.service

import com.example.parcialgrupo3whale.gateway.model.PetAPIResponse
import com.example.parcialgrupo3whale.gateway.model.PetRandomImageResponse
import retrofit2.http.GET
import retrofit2.Response

interface PetAPIService {
    @GET("/api/breeds/list/all")
    suspend fun getBreedsList() : Response<PetAPIResponse>

    @GET("api/breeds/image/random")
    fun getRandomImage() : Response<PetRandomImageResponse>

}