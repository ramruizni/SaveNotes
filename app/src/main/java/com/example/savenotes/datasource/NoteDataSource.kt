package com.example.savenotes.datasource

import com.example.savenotes.database.Note

interface NoteDataSource {

    suspend fun getAll(): List<Note>

    suspend fun insert(note: Note)

    suspend fun delete(note: Note)

}