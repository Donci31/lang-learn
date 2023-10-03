package hu.bme.aut.langlearn.presentation.practice_screen.main_practice_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import hu.bme.aut.langlearn.domain.Deck

data class PracticePageState(
    val practiceItem: PracticeItem
) {
    var selectedDeck by mutableStateOf(Deck(name = "Choose a deck"))
    var expanded by mutableStateOf(false)
}