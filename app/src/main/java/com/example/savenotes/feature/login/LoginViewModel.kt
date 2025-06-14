package com.example.savenotes.feature.login

import androidx.lifecycle.ViewModel
import com.example.savenotes.feature.main.MainState
import com.example.savenotes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class LoginState(
    val email: String = "",
)

@HiltViewModel
class LoginViewModel @Inject constructor(
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun setEmail(email: String) {
        _state.update { it.copy(email = email) }
    }
}