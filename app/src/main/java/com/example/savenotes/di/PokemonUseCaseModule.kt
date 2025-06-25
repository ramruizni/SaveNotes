package com.example.savenotes.di

import com.example.savenotes.domain.pokemon.usecases.FetchPokemonList
import com.example.savenotes.domain.pokemon.usecases.ObserveAllPokemon
import com.example.savenotes.domain.pokemon.usecases.RefreshPokemonList
import com.example.savenotes.domain.pokemon.usecases.SavePokemonList
import com.example.savenotes.remote.PokemonApi
import com.example.savenotes.repository.PokemonRepository
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
object PokemonUseCaseModule {

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
        pokemonRepository: PokemonRepository
    ) : FetchPokemonList = FetchPokemonList(
        repository = pokemonRepository
    )

    @Singleton
    @Provides
    fun provideSavePokemonList(
        pokemonRepository: PokemonRepository
    ) : SavePokemonList = SavePokemonList(
        repository = pokemonRepository
    )

    @Singleton
    @Provides
    fun provideObserveAllPokemon(
        pokemonRepository: PokemonRepository
    ) : ObserveAllPokemon = ObserveAllPokemon(
        repository = pokemonRepository
    )

    @Singleton
    @Provides
    fun provideRefreshPokemonList(
        fetchPokemonList: FetchPokemonList,
        savePokemonList: SavePokemonList
    ) : RefreshPokemonList = RefreshPokemonList(
        fetchPokemonList = fetchPokemonList,
        savePokemonList = savePokemonList
    )
}