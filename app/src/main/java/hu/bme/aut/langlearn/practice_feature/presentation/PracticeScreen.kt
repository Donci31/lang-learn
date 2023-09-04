package hu.bme.aut.langlearn.practice_feature.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import hu.bme.aut.langlearn.quiz_feature.presentation.DeckCard

@Composable
fun PracticeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DeckCard(name = "FlashCard")
        DeckCard(name = "Pronunciation PracticeScreen")
        DeckCard(name = "Listening")
        DeckCard(name = "Quiz")
        DeckCard(name = "Extend the sentence")
    }
}