package com.example.savenotes.repository

import com.example.savenotes.datasource.PokemonDataSource
import com.example.savenotes.domain.pokemon.models.Pokemon
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource
): PokemonRepository {

    override suspend fun fetchPokemonList(limit: Int, offset: Int): List<Pokemon> {
        return pokemonDataSource.fetchPokemonList(limit, offset)
    }

    override suspend fun savePokemonList(pokemon: List<Pokemon>) {
        pokemonDataSource.savePokemonList(pokemon)
    }

    override fun observeAllPokemon(): Flow<List<Pokemon>> {
        return pokemonDataSource.observeAllPokemon()
    }
}