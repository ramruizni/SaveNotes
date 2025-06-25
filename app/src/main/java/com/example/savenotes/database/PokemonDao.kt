package com.example.savenotes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.savenotes.database.dtos.PokemonDbDto
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    //'OnConflictStrategy.REPLACE' indica que si intentamos insertar un PokemonDbDto
    // que ya existe, es decir un pokemon con el mismo ID, la fila existente será reemplazada
    // por la nueva
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(pokemon: List<PokemonDbDto>)

    @Query("SELECT * FROM pokemon")
    fun observeAll(): Flow<List<PokemonDbDto>>
}