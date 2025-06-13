package com.example.savenotes.datasource

import com.example.savenotes.database.NoteDao
import com.example.savenotes.datasource.converters.toDbDto
import com.example.savenotes.datasource.converters.toNote
import com.example.savenotes.repository.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteDataSourceImpl(
    private val noteDao: NoteDao
) : NoteDataSource {

    override suspend fun getAll(): List<Note> {
        return noteDao.getAll().map { it.toNote() }
    }

    //En este caso, listoFlow sería cada List<NoteDbDto> perteneciente a la emisión actual del
    // Flow.
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