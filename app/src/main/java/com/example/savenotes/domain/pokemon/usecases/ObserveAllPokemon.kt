package com.example.savenotes.domain.pokemon.usecases

import com.example.savenotes.domain.pokemon.models.Pokemon
import com.example.savenotes.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class ObserveAllPokemon(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<List<Pokemon>> = repository.observeAllPokemon()
}