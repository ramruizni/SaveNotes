package com.example.savenotes.feature.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val state by viewModel.state.collectAsStateWithLifecycle()

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
                    //'navigate' es el metodo principal de NavController que se utiliza
                    // para cambiar al destino especificado
                    navController.navigate(
                        //'MainRoute' vendría siendo el destino al que queremos navegar
                        MainRoute(
                            email = state.email
                        )
                    )
                }
            ) {
                Text("Navigate to Main")
            }
        }
    }
}