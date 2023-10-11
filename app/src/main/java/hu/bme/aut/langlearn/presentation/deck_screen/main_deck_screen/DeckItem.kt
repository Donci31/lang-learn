package hu.bme.aut.langlearn.presentation.deck_screen.main_deck_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hu.bme.aut.langlearn.domain.DeckWithPractice

@Composable
fun DeckItem(
    deck: DeckWithPractice
) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { isExpanded = !isExpanded },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = deck.name,
                textAlign = TextAlign.Center
            )
            if (deck.practices.isEmpty()) {
                Text(
                    text = "No practice score yet"
                )
            } else {
                Text(
                    text = "Practice score: ${deck.practices.map { it.score }.average()}"
                )
            }
        }
        AnimatedVisibility(visible = isExpanded) {
            Column {
                Divider(thickness = 4.dp, color = MaterialTheme.colorScheme.onSurface)
                deck.words.forEach { word ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = word.foreignWord)
                        Text(text = word.englishTranslation)
                    }
                }
            }
        }
    }
}