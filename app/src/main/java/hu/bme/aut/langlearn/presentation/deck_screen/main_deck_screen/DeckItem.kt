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
import java.util.Date

@Composable
fun DeckItem(
    deck: DeckWithPractice,
) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = "${deck.flagEmoji} ${deck.name}",
                textAlign = TextAlign.Center
            )
            if (deck.practices.isEmpty()) {
                Text(
                    text = "No practice score yet"
                )
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ScoreBar(
                        rating = deck.practices.map { it.score }.average()
                    )
                    Text(
                        text = "Practice score"
                    )
                }
            }
        }
        if (deck.practices.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                text = "Last practice: ${
                    (Date().time - deck.practices.last().date.time) / (60 * 60 * 1000)
                }h ago",
                textAlign = TextAlign.Center
            )
        }
        AnimatedVisibility(visible = isExpanded) {
            Column {
                Divider(thickness = 4.dp, color = MaterialTheme.colorScheme.onSurface)
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        deck.words.map { it.foreignWord }.forEach { foreignWord ->
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                                text = foreignWord,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        deck.words.map { it.englishTranslation }.forEach { englishTranslation ->
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                                text = englishTranslation,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}