package com.example.savenotes.datasource

import com.example.savenotes.database.Note
import com.example.savenotes.database.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteDataSourceImpl(
    private val noteDao : NoteDao
) : NoteDataSource {

    override suspend fun getAll(): List<Note> {
        return noteDao.getAll()
    }

    override fun observeAll(): Flow<List<Note>> = noteDao.observeAll()

    override suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

}