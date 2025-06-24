package com.example.savenotes.domain.notes.usecases

import com.example.savenotes.domain.notes.models.Note
import com.example.savenotes.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class ObserveAllNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> = repository.observeAll()
}