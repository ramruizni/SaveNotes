package com.example.savenotes.domain.pokemon.usecases

import com.example.savenotes.domain.pokemon.models.Pokemon
import com.example.savenotes.repository.PokemonRepository

class FetchPokemonList(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): List<Pokemon> {
        return repository.fetchPokemonList(limit, offset)
    }
}
