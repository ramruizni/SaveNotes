package com.example.savenotes.datasource

import com.example.savenotes.database.NoteDao
import com.example.savenotes.datasource.converters.toDbDto
import com.example.savenotes.datasource.converters.toNote
import com.example.savenotes.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteDataSourceImpl(
    private val noteDao: NoteDao
) : NoteDataSource {

    override suspend fun getAll(): List<Note> {
        return noteDao.getAll().map { it.toNote() }
    }

    override fun observeAll(): Flow<List<Note>> = noteDao.observeAll().map { listFlow ->
        listFlow.map { it.toNote() }
    }

    override suspend fun insert(note: Note) {
        noteDao.insert(note.toDbDto())
    }

    override suspend fun delete(note: Note) {
        noteDao.delete(note.toDbDto())
    }
}