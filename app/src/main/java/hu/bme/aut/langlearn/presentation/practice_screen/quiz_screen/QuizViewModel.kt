package hu.bme.aut.langlearn.presentation.practice_screen.quiz_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.DeckRepository
import hu.bme.aut.langlearn.data.ProgressRepository
import hu.bme.aut.langlearn.domain.Practice
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeViewModel
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    deckRepository: DeckRepository,
    private val progressRepository: ProgressRepository
) : PracticeViewModel(
    savedStateHandle = savedStateHandle,
    repository = deckRepository
) {
    var userInput: String by mutableStateOf("")

    private var correctAnswerNumber: Int = 0

    init {
        progressRepository.createDeckPractice(deckId)
    }

    override fun goToNextWord() {
        userInput = ""
        super.goToNextWord()
    }

    fun checkCorrectAnswer() {
        if (
            userInput.lowercase(Locale.getDefault())
            ==
            cardList[currentCardIndex].englishTranslation.lowercase(Locale.getDefault())
        ) {
            correctAnswerNumber++
        }
    }

    fun saveProgress() {
        progressRepository.addPractice(
            practice = Practice(
                date = Date(),
                score = correctAnswerNumber.toDouble() / cardList.size
            ),
            deckId = deckId
        )
    }
}