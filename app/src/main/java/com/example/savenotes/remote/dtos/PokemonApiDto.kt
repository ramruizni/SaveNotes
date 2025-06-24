package com.example.savenotes.remote.dtos

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PokemonApiDto(
    //Esta es la clase encargada de mapear cada objeto individual dentro del array "results"
    // que resulta de 'PokemosResponse'
    //"name"  le indica a Gson que cuando encuentre un campo en el JSON llamado "name"
    // dentro del objeto que se está mapeando a Pokemon, su valor debe ser asignado a la
    // propiedad name de esta clase.
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)