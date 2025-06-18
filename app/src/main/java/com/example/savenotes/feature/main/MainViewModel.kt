package com.example.savenotes.feature.main

import androidx.lifecycle.SavedStateHandle
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

    val text: String = "",
)

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    init {
        val email = savedStateHandle.get<String>("email").orEmpty()
        viewModelScope.launch {
            _events.send(
                Event.ShowMessage("You used email: $email")
            )
        }
    }

    val notes = noteRepository
        .observeAll()
        .flowOn(Dispatchers.IO)
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