package com.example.savenotes.datasource

import com.example.savenotes.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {

    suspend fun getAll(): List<Note>

    fun observeAll(): Flow<List<Note>>

    suspend fun insert(note: Note)

    suspend fun delete(note: Note)

}