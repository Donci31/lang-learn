package hu.bme.aut.langlearn.presentation.practice_screen.flip_card_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.repositories.DeckRepository
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlipCardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    deckRepository: DeckRepository,
) : PracticeViewModel(
    savedStateHandle = savedStateHandle
) {

    init {
        viewModelScope.launch {
            deck = deckRepository.getDeck(deckId)
            cardList = deck?.words!!
        }
    }
}