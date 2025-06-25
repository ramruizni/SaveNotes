package com.example.savenotes.datasource.converters

import com.example.savenotes.database.dtos.PokemonDbDto
import com.example.savenotes.domain.pokemon.models.Pokemon
import com.example.savenotes.remote.dtos.PokemonApiDto

fun Pokemon.toDbDto(): PokemonDbDto {
    return PokemonDbDto(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}

fun PokemonDbDto.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}

fun PokemonApiDto.toPokemon(): Pokemon {
    val id = extractIdFromUrl(url)

    //La URL definida dentro de 'imageStartUrl' es una parte fija de la URL de imagen de arte
    // oficial de los Pokémon de la PokéAPI.
    val imageStartUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
    val imageEndUrl = ".png"
    val imageUrl = "$imageStartUrl$id$imageEndUrl"

    return Pokemon(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}

private fun extractIdFromUrl(url: String): Int {
    //'url.trimEnd('/')' elimina cualquier carácter que pueda estar al final de la URL.
    // '.split('/')' Divide la cadena de la URL en una lista de subcadenas, utilizando /
    // como delimitador. '.last()' obtiene el último elemento elemento de la lista resultante
    // de la división anterior (en este punto ya está capturado el número, pero en formato
    // string). Y '.toInt()' convierte la cadena del último elemento en un tipo Int.
    return url.trimEnd('/').split('/').last().toInt()
}