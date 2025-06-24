package com.example.savenotes.remote

import com.example.savenotes.remote.dtos.PokemonResponseApiDto
import retrofit2.http.GET
import retrofit2.http.Query

//En el contexto de Retrofit, el objetivo de esta interfaz será definir un conjunto de endpoints
// de la API con lo que podremos interactuar
interface PokemonApi {

    //@GET es una anotación de Retrofit que indica que el metodo getPokemonList realizará
    // una solicitud HTTP GET, y "pokemon" es la ruta relativa del endpoint al que se hará
    // la llamada
    @GET("pokemon")
    //Al usar una función suspend, Retrofit se encargará de ejecutar la llamada de red en un
    // hilo de fondo, y permite llamar a pokemonApi.getPokemonList() directamente desde una
    // coroutine
    suspend fun getPokemonList(
        //@Query es otra anotación de Retrofit, e indica que el parámetro "limit", por ejemplo,
        // se añadirá a la URL de la solicitud como parámetro de consulta.
        //'limit: Int = 100': Si no se definiera este parámetro, Kotlin le definiría igual el valor
        // de 100 por defecto
        @Query("limit") limit: Int,
        //'limit' especifica cuántos ítems obtener, y offset especifica cuántos ítmes saltar
        // desde el inicio de la lista total
        @Query("offset") offset: Int
        //La función getPokemonList debe retornar una lista del tipo PokemonResponse que es una
        // data class
    ): PokemonResponseApiDto
}