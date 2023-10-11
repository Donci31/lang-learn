package hu.bme.aut.langlearn.presentation.practice_screen.main_practice_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hu.bme.aut.langlearn.domain.Deck

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticePage(
    navController: NavController,
    deckNameList: List<Deck>,
    practicePageState: PracticePageState,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = practicePageState.practiceItem.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = practicePageState.practiceItem.description,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            ExposedDropdownMenuBox(
                expanded = practicePageState.expanded,
                onExpandedChange = {
                    practicePageState.expanded = !practicePageState.expanded
                }
            ) {
                TextField(
                    value = if (deckNameList.contains(practicePageState.selectedDeck)) {
                        practicePageState.selectedDeck.name
                    } else if (deckNameList.isNotEmpty()) {
                        practicePageState.selectedDeck = deckNameList.first()
                        practicePageState.selectedDeck.name
                    } else {
                        practicePageState.selectedDeck = Deck()
                        ""
                    },
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = practicePageState.expanded)
                    },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = practicePageState.expanded,
                    onDismissRequest = { practicePageState.expanded = false }
                ) {
                    deckNameList.forEach { deck ->
                        DropdownMenuItem(
                            text = { Text(text = deck.name) },
                            onClick = {
                                practicePageState.selectedDeck = deck
                                practicePageState.expanded = false
                            }
                        )
                    }
                }
            }
        }
        Button(
            onClick = {
                if (practicePageState.selectedDeck.name.isNotBlank()) {
                    navController.navigate(
                        "${practicePageState.practiceItem.destination}/${practicePageState.selectedDeck.id}"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth(fraction = 0.5f)
                .padding(16.dp)
        ) {
            Text(text = "Start Quiz")
        }
    }
}