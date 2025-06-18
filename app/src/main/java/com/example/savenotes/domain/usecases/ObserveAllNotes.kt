package com.example.savenotes.domain.usecases

import com.example.savenotes.domain.models.Note
import com.example.savenotes.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class ObserveAllNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> = repository.observeAll()
}