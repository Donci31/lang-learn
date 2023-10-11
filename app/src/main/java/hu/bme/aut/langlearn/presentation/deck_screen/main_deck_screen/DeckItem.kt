package hu.bme.aut.langlearn.presentation.deck_screen.main_deck_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hu.bme.aut.langlearn.domain.DeckWithPractice

@Composable
fun DeckItem(
    deck: DeckWithPractice,
    onClick: (DeckWithPractice) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(deck) },
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

    }
}