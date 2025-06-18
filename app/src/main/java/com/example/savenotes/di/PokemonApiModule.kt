package com.example.savenotes.di

import com.example.savenotes.PokemonApi
import com.example.savenotes.domain.login.usecases.ValidateEmail
import com.example.savenotes.domain.pokemon.usecases.FetchPokemonList
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Provides
    @Singleton
    fun providePokemonApi(retrofit: Retrofit): PokemonApi =
        retrofit.create(PokemonApi::class.java)

    @Singleton
    @Provides
    fun provideFetchPokemonList(
        pokemonApi: PokemonApi
    ) : FetchPokemonList = FetchPokemonList(
        api = pokemonApi
    )
}