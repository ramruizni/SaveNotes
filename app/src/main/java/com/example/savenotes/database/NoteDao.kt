package com.example.savenotes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<NoteDbDto>

    @Query("SELECT * FROM notes")
    fun observeAll(): Flow<List<NoteDbDto>>

    @Insert
    suspend fun insert(note: NoteDbDto)

    @Delete
    suspend fun delete(note: NoteDbDto)
}