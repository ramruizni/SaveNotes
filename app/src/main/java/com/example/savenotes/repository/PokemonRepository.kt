package com.example.savenotes.repository

import com.example.savenotes.domain.pokemon.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun fetchPokemonList(limit: Int, offset: Int): List<Pokemon>

    suspend fun savePokemonList(pokemon: List<Pokemon>)

    fun observeAllPokemon(): Flow<List<Pokemon>>
}