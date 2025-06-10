package com.example.savenotes.repository

import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun getAll(): List<Note>

    fun observeAll(): Flow<List<Note>>

    suspend fun insert(note: Note)

    suspend fun delete(note: Note)

}