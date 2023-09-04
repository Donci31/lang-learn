package hu.bme.aut.langlearn.ui.practice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import hu.bme.aut.langlearn.ui.quiz.DeckCard

@Composable
fun PracticeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DeckCard(name = "FlashCard")
        DeckCard(name = "Pronunciation PracticeScreen")
        DeckCard(name = "Listenning")
        DeckCard(name = "Quiz")
    }
}