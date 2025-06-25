package com.example.savenotes.remote

import com.example.savenotes.remote.dtos.PokemonResponseApiDto
import retrofit2.http.GET
import retrofit2.http.Query


interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonResponseApiDto
}