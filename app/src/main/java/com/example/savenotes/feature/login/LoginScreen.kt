package com.example.savenotes.feature.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.savenotes.navigation.main.MainRoute

@Composable
fun LoginScreen(
    viewModel: LoginViewModel  = hiltViewModel(),
    navController: NavHostController
){
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
            Button(
                onClick = {
                    navController.navigate(MainRoute)
                }
            ) {
                Text("Navigate to Main")
            }
        }
    }
}