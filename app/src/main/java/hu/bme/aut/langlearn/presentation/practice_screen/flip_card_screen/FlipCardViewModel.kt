package hu.bme.aut.langlearn.presentation.practice_screen.flip_card_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.domain.practice_screen.GetDeckUseCase
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlipCardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getDeckUseCase: GetDeckUseCase
) : PracticeViewModel(
    savedStateHandle = savedStateHandle
) {

    init {
        viewModelScope.launch {
            deck = getDeckUseCase(deckId)
            cardList = deck?.words!!
        }
    }
}