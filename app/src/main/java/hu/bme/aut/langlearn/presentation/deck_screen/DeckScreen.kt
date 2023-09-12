package hu.bme.aut.langlearn.presentation.deck_screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DecksScreen(
    navController: NavController,
    viewModel: DeckViewModel = hiltViewModel()
) {
    val decks = viewModel.deckListState.collectAsState()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(all = 16.dp),
                onClick = { viewModel.onAddDeckClick() }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Add icon"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Add new deck")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = padding,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(decks.value.decks) { deck ->
                DeckItem(deck = deck, onClick = { viewModel.onDeckClick(deck) })
            }
        }
    }
}