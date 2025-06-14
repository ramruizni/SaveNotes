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

    val text: String = "",
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

    //Este es el nuevo interfaz de notes, pero generado a partir de Flowoperators
    // Forma 2
    val notes = noteRepository
        .observeAll() //Este Flow emitirá una nueva lista de notas (List<Note>) cada vez
                    // que los datos de la tabla de notas cambien en la base de datos.
        .flowOn(Dispatchers.IO) //Con este flowoperator todas las operaciones upstream se
                                // ejecutarán en un hilo del pool de Dispatchers.IO, sin
                                // embargo las operaciones downstream no se verán afectadas
                                // y se ejecutarán en el contexto del colector, a menos que
                                // se use otro flowOn más adelante.
        //.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
        //.statteIn convierte un Flow "Frío", en un StateFlow (caliente). Este StateFlow mantiene
        // un único valor de estado actual y emite actualizaciones de ese estado a sus colectores
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
        // viewModelScope representa el CoroutineScope en el que se lanzará la coroutine
        // SharingStarted.WhileSubscribed(5000) define la estrategia para iniciar y detener
        // la colección del Flow aguas arriba, la cual dice que el Flow comenzará a colectarse
        // cuando haya al menos un colector activo, pero si este colcetor baja a cero, el Flow
        // no se detiene automáticamente, sino que espera 5 segundos para hacer esto , pero si
        // dentro de esos 5 segundos se suscribe un nuevo colector, la colección del Flow aguas
        // arriba continuaría (esto es particularmente útil par alas rotaciones de pantalla)
        //emptyList() define el valor inicial del StateFlow<List<Note>> antes de que se emita el
        // primer valor

    val notesCount = notes
        //map es un flowoperator que transforma cada valor emitido por el Flow, es decir, cada
        // List<Note> en ete caso
        .map {
            it.size
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val buttonEnabled = _state.map {
        //isNotEmpty() Es un metodo estándar de Kotlin para Strings que devuelve true si
        // la cadena tiene al menos un carácter, y false si está vacía.
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