package com.example.savenotes.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savenotes.domain.login.usecases.ValidateEmail
import com.example.savenotes.domain.pokemon.usecases.FetchPokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginState(
    val email: String = "",
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val fetchPokemonList: FetchPokemonList
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    fun setEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onLoginClick() {
        val error = validateEmail(_state.value.email)

        viewModelScope.launch {
            _events.send(
                if (error != null) Event.ShowError(message = error)
                else Event.NavigateToMain
            )
        }
    }

    fun onPokemonListClick() {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonList = fetchPokemonList()
            val pokemonListMessage = pokemonList.map { it.name }.joinToString(" ")
            _events.send(Event.ShowError(message = pokemonListMessage))
        }
    }

    sealed class Event {
        data class ShowError(val message: String): Event()
        data object NavigateToMain: Event()
    }
}