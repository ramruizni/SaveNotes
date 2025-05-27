package com.example.savenotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

data class Note(
    val text: String,
)

class MainViewModel: ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    init {
        // no sirve
        //val list = listOf<String>()
        //list =+ "Hello"

        val list = mutableListOf<String>()
        list += "Hello"

        // Homework
        // Haga lo mismo con un LazyVerticalGrid
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

        //val newList = _notes.value.toMutableList()
        //newList.add(Note(text))
        //_notes.update { newList }

        _notes.update { it + Note(text) }

        _state.update { it.copy(text = "") }
    }

    sealed class Event {
        data class ShowMessage(val message: String): Event()
    }
}