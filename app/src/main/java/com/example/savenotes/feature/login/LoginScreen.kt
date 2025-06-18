package com.example.savenotes.feature.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.savenotes.navigation.main.MainRoute

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(context) {
        viewModel.events.collect { event ->
            when(event) {
                is LoginViewModel.Event.NavigateToMain -> {
                    navController.navigate(
                        MainRoute(
                            email = state.email
                        )
                    )
                }
                is LoginViewModel.Event.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login Screen",
            )
            Spacer(modifier = Modifier.weight(1f))
            TextField(
                value = state.email,
                onValueChange = { email ->
                    viewModel.setEmail(email)
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.onLoginClick()
                }
            ) {
                Text("Navigate to Main")
            }
        }
    }
}