package com.example.parcialgrupo3whale.gateway.model

import com.google.gson.annotations.SerializedName

data class PetRandomImageResponse(
    @SerializedName("message") val message: String?,
    @SerializedName("status") val status: String?
)
