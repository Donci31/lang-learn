package hu.bme.aut.langlearn.presentation.practice_screen.main_practice_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.repositories.DeckRepository
import javax.inject.Inject

@HiltViewModel
class PracticeScreenViewModel @Inject constructor(
    repository: DeckRepository,
) : ViewModel() {

    val practiceItems = listOf(
        PracticeItem(
            name = "FlipCard",
            description = "Flipping card word practice",
            destination = "flip_card_screen"
        ),
        PracticeItem(
            name = "Quiz",
            description = "Type the correct translation",
            destination = "quiz_screen"
        ),
        PracticeItem(
            name = "Sentence",
            description = "Complete the sentence",
            destination = "sentence_screen"
        )
    )

    val practicePageStates = practiceItems.map { practiceItem ->
        PracticePageState(practiceItem)
    }

    val decks = repository.getAllDecks()
}