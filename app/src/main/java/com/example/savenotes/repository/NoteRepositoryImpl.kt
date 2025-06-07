package com.example.savenotes.repository

import com.example.savenotes.database.Note
import com.example.savenotes.datasource.NoteDataSource

class NoteRepositoryImpl(
    private val noteDataSource: NoteDataSource,
) : NoteRepository {

    override suspend fun getAll(): List<Note> {
        return noteDataSource.getAll()
    }

    override suspend fun insert(note: Note) {
        noteDataSource.insert(note)
    }

    override suspend fun delete(note: Note) {
        noteDataSource.delete(note)
    }
}