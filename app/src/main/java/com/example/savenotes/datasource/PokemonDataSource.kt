package com.example.savenotes.datasource

import com.example.savenotes.domain.pokemon.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonDataSource {

    suspend fun fetchPokemonList(limit: Int, offset: Int): List<Pokemon>

    suspend fun savePokemonList(pokemon: List<Pokemon>)

    fun observeAllPokemon(): Flow<List<Pokemon>>
}