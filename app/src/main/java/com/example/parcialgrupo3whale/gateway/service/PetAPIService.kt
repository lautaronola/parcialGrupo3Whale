package com.example.parcialgrupo3whale.gateway.service

import com.example.parcialgrupo3whale.gateway.model.PetBreedsAPIResponse
import com.example.parcialgrupo3whale.gateway.model.PetRandomImageResponse
import com.example.parcialgrupo3whale.gateway.model.PetSubBreedAPIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PetAPIService {
    @GET("/api/breeds/list/all")
    fun getBreedsList() : Call<PetBreedsAPIResponse>

    @GET("api/breeds/image/random")
    fun getRandomImage() : Call<PetRandomImageResponse>

    @GET("api/breed/{breed}/list")
    fun getSubBreed(@Path("breed") breed: String) : Call<PetSubBreedAPIResponse>
}