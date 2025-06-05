package com.example.savenotes.repository

import android.content.Context
import com.example.savenotes.database.Note
import com.example.savenotes.datasource.NoteDataSource
import com.example.savenotes.datasource.NoteDataSourceImpl

class NoteRepositoryImpl(
    context: Context,
) : NoteRepository {

    private var noteDataSource : NoteDataSource? = null

    init {
        noteDataSource = NoteDataSourceImpl(context)
    }

    override suspend fun getAll(): List<Note> {
        return noteDataSource?.getAll().orEmpty()
    }

    override suspend fun insert(note: Note) {
        noteDataSource?.insert(note)
    }

    override suspend fun delete(note: Note) {
        noteDataSource?.delete(note)
    }

}