package com.example.savenotes.feature.pokemonlist

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.savenotes.feature.pokemonlist.composable.PokemonItem
import com.example.savenotes.ui.composable.box.BoxLoadingIndicator

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
) {
   val context = LocalContext.current

    val state by viewModel.state.collectAsStateWithLifecycle()
    val pokemonList by viewModel.pokemonList.collectAsStateWithLifecycle()

    LaunchedEffect(context) {
        viewModel.events.collect { event ->
            when(event) {
                is PokemonListViewModel.Event.ShowMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(
                    count = pokemonList.size,
                    //key = { pokemonList[it].id }
                ) {
                    PokemonItem(pokemon = pokemonList[it])
                }
            }
        }
        BoxLoadingIndicator(isLoading = state.isLoading)
    }
}