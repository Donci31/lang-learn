package hu.bme.aut.langlearn.presentation.practice_screen.sentence_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.repositories.DeckRepository
import hu.bme.aut.langlearn.data.repositories.ProgressRepository
import hu.bme.aut.langlearn.data.repositories.SentenceRepository
import hu.bme.aut.langlearn.domain.Practice
import hu.bme.aut.langlearn.domain.Word
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeViewModel
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SentenceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    deckRepository: DeckRepository,
    private val progressRepository: ProgressRepository,
    private val sentenceRepository: SentenceRepository,
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
            deck = deckRepository.getDeck(deckId)
            cardList = deck?.words!!
            languageCode = Locale(deck?.languageCode!!).displayLanguage
            resetQuiz()
        }
    }

    private fun getSentence() {
        correctAnswerIndex = java.util.Random().nextInt(4)
        val word = quizAnswers[correctAnswerIndex].foreignWord

        viewModelScope.launch {
            curSentence = sentenceRepository.getSentence(word, languageCode)
                .replace(
                    Regex("\\b${Regex.escape(word)}\\b", RegexOption.IGNORE_CASE),
                    "_".repeat(5)
                )
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
        progressRepository.addPractice(
            deckId = deckId,
            practice = Practice(
                date = Date(),
                score = correctAnswerNumber.toDouble() / cardList.size
            )
        )
    }
}