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
    return url.trimEnd('/').split('/').last().toInt()
}