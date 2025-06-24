package com.example.savenotes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.savenotes.database.dtos.NoteDbDto
import com.example.savenotes.database.dtos.PokemonDbDto

@Database(
    entities = [
        NoteDbDto::class,
        PokemonDbDto::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun pokemonDao(): PokemonDao
}