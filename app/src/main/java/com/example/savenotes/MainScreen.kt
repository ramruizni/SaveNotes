package com.example.savenotes

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current

    val state by viewModel.state.collectAsStateWithLifecycle()
    val notes by viewModel.notes.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = context) {
        viewModel.events.collect { event ->
            when (event) {
                is MainViewModel.Event.ShowMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            TextField(
                value = state.text,
                onValueChange = { text ->
                    viewModel.setText(text)
                }
            )
            Button(
                onClick = { viewModel.addNote() }
            ) {
                Text(
                    text = "Add note"
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(
                    count = notes.size
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = notes[it].text
                    )
                }
            }
        }

    }
}