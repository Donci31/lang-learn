package hu.bme.aut.langlearn.presentation.practice_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.FirestoreRepository
import hu.bme.aut.langlearn.domain.Word
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(
    private val repository: FirestoreRepository
) : ViewModel() {
    private var currentCardIndex = 0

    private val cardList = listOf(
        Word(
            foreignWord = "Bueno",
            englishTranslation = "Good"
        ),
        Word(
            foreignWord = "Hola",
            englishTranslation = "Hello"
        ),
    )

    fun getProgress(): Float = (currentCardIndex + 1).toFloat() / cardList.size

    fun getForeignWord(): String = cardList[currentCardIndex].foreignWord

    fun getEnglishTranslation(): String = cardList[currentCardIndex].englishTranslation

    fun isLastWord(): Boolean = currentCardIndex == cardList.size - 1

    fun goToNextWord() {
        if (currentCardIndex < cardList.size - 1) {
            currentCardIndex++
        }
    }

    fun goToPreviousWord() {
        if (currentCardIndex > 0) {
            currentCardIndex--
        }
    }

    fun isNotFirstWord(): Boolean = currentCardIndex > 0
}