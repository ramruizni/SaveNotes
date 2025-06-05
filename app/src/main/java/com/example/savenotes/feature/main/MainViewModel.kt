package com.example.savenotes.feature.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savenotes.database.Note
import com.example.savenotes.repository.NoteRepository
import com.example.savenotes.repository.NoteRepositoryImpl
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

    private var noteRepository: NoteRepository? = null

    fun initializeRepository(context: Context) {
        noteRepository = NoteRepositoryImpl(context)

        refreshNotes()
    }

    private fun refreshNotes() {
        //'viewModelScope' proporciona un CoroutineScope que está vinculado al ciclo
        // de vida del ViewModel.
        //'.launch(...)' es un constructor de coroutines. Lanza una nueva coroutine sin
        // bloquear el hilo actual.
        //Dispatchers.IO es un despachador que está optimizado para operaciones de
        // entrada/salida (I/O) que consumen mucho tiempo, como operaciones de red,
        // lectura/escritura de archivos, y operaciones de base de datos. Entonces con
        // esto nos aseguramos que la llamada a la base de datos no se ejecute en el hilo
        // principal, evitando bloquear la UI
        viewModelScope.launch(Dispatchers.IO) {
            //El operador ?. permite llamar a getAll() solo si noteDao no es nulo.
            // getAll(): Este es el metodo suspend que definimos en el NoteDao para
            // obtener todas las notas de la base de datos. Devuelve List<Note>.
            //'?: emptyList()' significa que si noteDao es nulo, entonces
            // emptyList() se ejecutará, y notesInDb se asignará a una lista vacía.
            val notesInDb = noteRepository?.getAll() ?: emptyList()
            _notes.update { notesInDb }
        }
    }

    private fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            //? se utiliza porque noteDao podría ser nulo. Si noteDao es nulo, la
            // llamada a insert(note) simplemente no ocurrirá, evitando un
            // NullPointerException.
            //' noteDao?.insert(note)' llama al metodo suspend fun insert(note: Note),
            // y luego room se encarga de ejecutar esta operación de inserción en la
            // base de datos SQLite en un hilo de fondo.
            noteRepository?.insert(note)
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