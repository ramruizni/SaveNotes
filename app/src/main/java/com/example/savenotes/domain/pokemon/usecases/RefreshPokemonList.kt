package com.example.savenotes.domain.pokemon.usecases

class RefreshPokemonList(
    private val fetchPokemonList: FetchPokemonList,
    private val savePokemonList: SavePokemonList
) {
    suspend operator fun invoke(limit: Int = 20, offset: Int = 0) {
        savePokemonList(fetchPokemonList(limit, offset))
    }
}