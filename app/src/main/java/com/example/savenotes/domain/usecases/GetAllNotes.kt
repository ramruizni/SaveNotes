package com.example.savenotes.domain.usecases

import com.example.savenotes.domain.models.Note
import com.example.savenotes.repository.NoteRepository

class GetAllNotes(
    private val repository: NoteRepository
) {

    suspend fun invoke(): List<Note> {
        return repository.getAll()
    }
}