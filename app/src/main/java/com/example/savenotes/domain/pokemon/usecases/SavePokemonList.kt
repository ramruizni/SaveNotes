package com.example.savenotes.domain.pokemon.usecases

import com.example.savenotes.domain.pokemon.models.Pokemon
import com.example.savenotes.repository.PokemonRepository

class SavePokemonList(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(pokemon: List<Pokemon>) {
        repository.savePokemonList(pokemon)
    }
}