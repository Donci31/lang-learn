package hu.bme.aut.langlearn.presentation.practice_screen.sentence_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.domain.entities.Word
import hu.bme.aut.langlearn.domain.practice_screen.GetDeckUseCase
import hu.bme.aut.langlearn.domain.practice_screen.GetSentenceUseCase
import hu.bme.aut.langlearn.domain.practice_screen.SaveProgressUseCase
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SentenceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getDeckUseCase: GetDeckUseCase,
    private val saveProgressUseCase: SaveProgressUseCase,
    private val getSentenceUseCase: GetSentenceUseCase
) : PracticeViewModel(
    savedStateHandle = savedStateHandle
) {
    var curSentence: String? by mutableStateOf(null)

    lateinit var quizAnswers: List<Word>

    private lateinit var languageCode: String

    private var correctAnswerIndex: Int = 0

    private var correctAnswerNumber: Int = 0

    init {
        viewModelScope.launch {
            deck = getDeckUseCase(deckId)
            cardList = deck?.words!!
            languageCode = Locale(deck?.languageCode!!).displayLanguage
            resetQuiz()
        }
    }

    private fun getSentence() {
        correctAnswerIndex = java.util.Random().nextInt(4)
        val word = quizAnswers[correctAnswerIndex].foreignWord

        viewModelScope.launch {
            curSentence = getSentenceUseCase(word, languageCode)
        }
    }

    fun resetQuiz() {
        curSentence = null
        quizAnswers = cardList.shuffled().take(4)
        getSentence()
    }

    fun checkCorrectAnswer(word: Word) {
        if (word == quizAnswers[correctAnswerIndex]) {
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