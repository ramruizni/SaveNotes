package com.example.savenotes.domain.notes.usecases

import com.example.savenotes.domain.notes.models.Note
import com.example.savenotes.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class ObserveAllNotes(
    private val repository: NoteRepository
) {
    //Cuando una clase tiene una función invoke marcada con operator, podemos llamar a
    // las instancias de esa clase como si fueran funciones, y es por esto en el
    // MainViewModel recibimos la instancia observeAllNotes de tipo ObserveAllNotes, que
    // luego es usada como una función
    operator fun invoke(): Flow<List<Note>> = repository.observeAll()
}