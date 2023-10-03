package hu.bme.aut.langlearn.presentation.practice_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.FirestoreRepository
import hu.bme.aut.langlearn.domain.Deck
import hu.bme.aut.langlearn.domain.Word
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: FirestoreRepository
) : ViewModel() {
    private val deckId: String = checkNotNull(savedStateHandle["deckId"])

    private var currentCardIndex by mutableIntStateOf(0)

    var deck: Deck? = null

    private lateinit var cardList: List<Word>

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