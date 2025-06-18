package com.example.savenotes.domain.pokemon.models

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name") val name: String
)