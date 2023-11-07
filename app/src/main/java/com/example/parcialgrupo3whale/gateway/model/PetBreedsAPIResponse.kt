package com.example.parcialgrupo3whale.gateway.model

import com.google.gson.annotations.SerializedName

data class PetBreedsAPIResponse(
    @SerializedName("message") val message: Map<String, List<String>>,
    @SerializedName("status") val status: String
)
