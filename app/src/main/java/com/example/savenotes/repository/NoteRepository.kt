package com.example.savenotes.repository

import com.example.savenotes.database.Note

interface NoteRepository {

    suspend fun getAll(): List<Note>

    suspend fun insert(note: Note)

    suspend fun delete(note: Note)

}