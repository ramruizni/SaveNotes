package com.example.savenotes.domain.pokemon.models

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("results") val results: List<Pokemon>
)


