package com.example.savenotes.domain.pokemon.usecases

import com.example.savenotes.PokemonApi
import com.example.savenotes.domain.pokemon.models.Pokemon

class FetchPokemonList(
    private val api: PokemonApi
) {
    suspend operator fun invoke(): List<Pokemon> {
        return api.getPokemonList().results
    }
}