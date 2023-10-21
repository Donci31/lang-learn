package hu.bme.aut.langlearn.presentation.practice_screen.quiz_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.repositories.DeckRepository
import hu.bme.aut.langlearn.data.repositories.ProgressRepository
import hu.bme.aut.langlearn.domain.Practice
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeViewModel
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    deckRepository: DeckRepository,
    private val progressRepository: ProgressRepository
) : PracticeViewModel(
    savedStateHandle = savedStateHandle
) {
    var userInput: String by mutableStateOf("")

    private var correctAnswerNumber: Int = 0

    init {
        viewModelScope.launch {
            deck = deckRepository.getDeck(deckId)
            cardList = deck?.words!!
            progressRepository.createDeckPractice(deckId)
        }
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