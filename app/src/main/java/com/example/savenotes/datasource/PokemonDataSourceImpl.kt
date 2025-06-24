package com.example.savenotes.datasource

import com.example.savenotes.database.PokemonDao
import com.example.savenotes.datasource.converters.toDbDto
import com.example.savenotes.datasource.converters.toPokemon
import com.example.savenotes.domain.pokemon.models.Pokemon
import com.example.savenotes.remote.PokemonApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonDataSourceImpl(
    private val dao: PokemonDao,
    private val api: PokemonApi
) : PokemonDataSource {

    override suspend fun fetchPokemonList(limit: Int, offset: Int): List<Pokemon> {
        return api.getPokemonList(limit, offset).results.map { it.toPokemon() }
    }

    override suspend fun savePokemonList(pokemon: List<Pokemon>) {
        dao.saveAll(pokemon.map { it.toDbDto() })
    }

    override fun observeAllPokemon(): Flow<List<Pokemon>> = dao.observeAll().map { listFlow ->
        listFlow.map { it.toPokemon() }
    }
}