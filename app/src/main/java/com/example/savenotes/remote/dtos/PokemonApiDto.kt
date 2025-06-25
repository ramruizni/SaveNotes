package com.example.savenotes.remote.dtos

import com.google.gson.annotations.SerializedName

data class PokemonApiDto(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)