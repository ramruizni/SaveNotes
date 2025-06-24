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
    //Con este metodo obtenemos una instancia de retrofit
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        //La url pasada a 'baseUrl' va a ser la base para todas las llamadas de red realizadas
        // por la instanci de Retrofit
        .baseUrl("https://pokeapi.co/api/v2/")
        //'GsonConverterFactory' es el convertidor para la biblioteca GSON, y
        // 'GsonBuilder().create()' crea una instancia de Gson. Esta parte en general se
        // encarga de convertir las respuesta JSON en objetos Kotlin y viceversa.
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        // build termina de construir la instancia de Retrofit
        .build()

    @Provides
    @Singleton
    fun providePokemonApi(retrofit: Retrofit): PokemonApi =
        //Este es un metodo estándar de Retrofit para crear una implementación concreta de una
        // interfaz de API, que en este caso es PokemoApi. Así que acá Retrofit genera el código
        // necesario para manejar las llamadas de red definidas en la interfaz
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