package hu.bme.aut.langlearn.presentation.deck_screen.main_deck_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.domain.deck_screen.CombineDecksWithPracticesUseCase
import javax.inject.Inject

@HiltViewModel
class DeckViewModel @Inject constructor(
    combineDecksWithPracticesUseCase: CombineDecksWithPracticesUseCase
) : ViewModel() {

    val combinedList = combineDecksWithPracticesUseCase()

}