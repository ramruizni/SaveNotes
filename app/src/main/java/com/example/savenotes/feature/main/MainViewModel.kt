package com.example.savenotes.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savenotes.repository.Note
import com.example.savenotes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainState(
    val isLoading: Boolean = false,

    val text: String = ""
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    // Forma 1
    /*private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.observeAll().collect { notesInDb ->
                _notes.update { notesInDb }
            }
        }
    }*/

    // Forma 2
    val notes = noteRepository
        .observeAll()
        .flowOn(Dispatchers.IO)
        //.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val notesCount = notes
        .map {
            it.size
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val buttonEnabled = _state.map {
        it.text.isNotEmpty()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    private fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            //? se utiliza porque noteDao podría ser nulo. Si noteDao es nulo, la
            // llamada a insert(note) simplemente no ocurrirá, evitando un
            // NullPointerException.
            //' noteDao?.insert(note)' llama al metodo suspend fun insert(note: Note),
            // y luego room se encarga de ejecutar esta operación de inserción en la
            // base de datos SQLite en un hilo de fondo.
            noteRepository.insert(note)
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

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.delete(note)
            _events.send(Event.ShowMessage("Deleted note!"))
        }
    }

    sealed class Event {
        data class ShowMessage(val message: String) : Event()
    }
}