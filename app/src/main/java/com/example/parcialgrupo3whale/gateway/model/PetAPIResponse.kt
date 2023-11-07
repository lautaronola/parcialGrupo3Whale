package com.example.parcialgrupo3whale.gateway.model

import com.google.gson.annotations.SerializedName

data class PetAPIResponse(
    @SerializedName("message") val message: Map<String, List<String>>,
    @SerializedName("status") val status: String
)
