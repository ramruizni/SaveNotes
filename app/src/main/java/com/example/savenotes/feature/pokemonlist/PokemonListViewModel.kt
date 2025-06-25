package com.example.savenotes.feature.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savenotes.domain.pokemon.usecases.ObserveAllPokemon
import com.example.savenotes.domain.pokemon.usecases.RefreshPokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PokemonListState(
    val isLoading: Boolean = false,
)

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val refreshPokemonList: RefreshPokemonList,
    observeAllPokemon: ObserveAllPokemon
): ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state = _state.asStateFlow()

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    val pokemonList = observeAllPokemon()
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            //El bloque 'try-catch' se utiliza para el manejo de excepciones, pues
            // 'refreshPokemonList()' implica una llamado a red quepodría presentar varios
            // errores
            try {
                refreshPokemonList()
                //El bloque 'catch' se ejecutaría en caso de que el código dentro del bloque
                // 'try' lanzace una excepción. Esta excepción quedaría guardada dentro de 'e'
            } catch (e: Exception) {
                //'e.message' obtiene el mensaje descriptivo de la excepción, y '.orEmpty()'
                // devolvería una cadena vacía si el valor de 'e.message' fuese 'null', esto
                // con el objetivo de evitar un NullPointerException.
                _events.send(Event.ShowMessage(e.message.orEmpty()))
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    sealed class Event {
        data class ShowMessage(val message: String) : Event()
    }
}

