package com.example.savenotes.remote.dtos

import com.google.gson.annotations.SerializedName

data class PokemonResponseApiDto(
    @SerializedName("results") val results: List<PokemonApiDto>
)

