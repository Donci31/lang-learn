package hu.bme.aut.langlearn.presentation.deck_screen.add_deck_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.bme.aut.langlearn.navigation.NavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDeckScreen(
    navController: NavController,
    viewModel: AddDeckViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Add deck")
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.addNewDeck()
                        navController.navigate(NavItem.Quiz.route) {
                            popUpTo(navController.graph.id)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Send,
                            contentDescription = "SaveDeck"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = viewModel::addNewWord
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Add icon"
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TextField(
                    value = viewModel.deckName,
                    onValueChange = { viewModel.deckName = it },
                    label = { Text(text = "Deck Name") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            items(viewModel.statefulWords) { word ->
                WordItem(word = word)
            }
        }
    }
}
