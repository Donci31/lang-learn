package hu.bme.aut.langlearn.presentation.practice_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import hu.bme.aut.langlearn.domain.Word

open class PracticeViewModel : ViewModel() {
    protected var currentCardIndex by mutableIntStateOf(0)

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

    fun goToNextCard() {
        if (currentCardIndex < cardList.size - 1) {
            currentCardIndex++
        }
    }
}