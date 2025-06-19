package com.example.savenotes.domain.notes.usecases

import com.example.savenotes.domain.notes.models.Note
import com.example.savenotes.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.delete(note)
    }
}