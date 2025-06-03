package com.example.savenotes.feature.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.savenotes.database.AppDatabase
import com.example.savenotes.database.Note
import com.example.savenotes.database.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainState(
    val isLoading: Boolean = false,

    val text: String = ""
)

class MainViewModel: ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    private var noteDao: NoteDao? = null

    fun initializeDatabase(context: Context) {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "notes-db"
        ).build()

        noteDao = db.noteDao()

        refreshNotes()
    }

    private fun refreshNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val notesInDb = noteDao?.getAll() ?: emptyList()
            _notes.update { notesInDb }
        }
    }

    private fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao?.insert(note)
            refreshNotes()
        }
    }

    fun setText(text: String) {
        _state.update { it.copy(text = text) }
    }

    fun addNote() {
        val text = _state.value.text
        if (text == "") {
            viewModelScope.launch {
                _events.send(Event.ShowMessage("Text is empty"))
            }
            return
        }

        insertNote(Note(text = text))

        _state.update { it.copy(text = "") }
    }

    sealed class Event {
        data class ShowMessage(val message: String): Event()
    }
}