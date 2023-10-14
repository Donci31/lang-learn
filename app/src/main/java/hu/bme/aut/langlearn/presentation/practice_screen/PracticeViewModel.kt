package hu.bme.aut.langlearn.presentation.practice_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.DeckRepository
import hu.bme.aut.langlearn.domain.Deck
import hu.bme.aut.langlearn.domain.Word
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class PracticeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: DeckRepository
) : ViewModel() {
    protected val deckId: String = checkNotNull(savedStateHandle["deckId"])

    protected var currentCardIndex by mutableIntStateOf(0)

    var deck: Deck? = null

    protected lateinit var cardList: List<Word>

    init {
        viewModelScope.launch {
            deck = repository.getDeck(deckId)
            cardList = deck?.words!!
        }
    }

    fun getProgress(): Float = (currentCardIndex + 1).toFloat() / cardList.size

    fun getForeignWord(): String = cardList[currentCardIndex].foreignWord

    fun getEnglishTranslation(): String = cardList[currentCardIndex].englishTranslation

    fun isLastWord(): Boolean = currentCardIndex == cardList.size - 1

    open fun goToNextWord() {
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