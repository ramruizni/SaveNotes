package com.example.savenotes.domain.pokemon.models

import com.google.gson.annotations.SerializedName

//Esta clase está para mapear la estructura de una parte específica de la
// respuesta JSON que esperamos recibir de la API
data class PokemonResponse(
    //@SerializedName indica cómo se debe mapear un campo del JSON a una propiedad de nuestra
    // clase KOTLIN. "results" le dice a Gson que cuando encuentre un campo del JSON llamado
    // "results", su valor debe ser asignado a la propiedad results de esta clase
    // 'PokemonResponse'
    @SerializedName("results") val results: List<Pokemon>
    //Cada objeto dentro del array JSON será deserializado en una instancia de la clase
    // "Pokemon", entonces GSON intentará parsear "results" en un objeto Pokemon
)

