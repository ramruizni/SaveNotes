package com.example.savenotes.domain.notes.usecases

import com.example.savenotes.domain.notes.models.Note
import com.example.savenotes.repository.NoteRepository

class GetAllNotes(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(): List<Note> {
        return repository.getAll()
    }
}