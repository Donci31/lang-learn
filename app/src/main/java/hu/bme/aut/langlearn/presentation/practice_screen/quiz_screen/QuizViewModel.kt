package hu.bme.aut.langlearn.presentation.practice_screen.quiz_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.domain.practice_screen.GetDeckUseCase
import hu.bme.aut.langlearn.domain.practice_screen.SaveProgressUseCase
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getDeckUseCase: GetDeckUseCase,
    private val saveProgressUseCase: SaveProgressUseCase,
) : PracticeViewModel(
    savedStateHandle = savedStateHandle
) {
    var userInput: String by mutableStateOf("")

    private var correctAnswerNumber: Int = 0

    init {
        viewModelScope.launch {
            deck = getDeckUseCase(deckId)
            cardList = deck?.words!!
        }
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
        saveProgressUseCase(
            deckId = deckId,
            correctAnswerNumber = correctAnswerNumber,
            cardListSize = cardList.size
        )
    }
}