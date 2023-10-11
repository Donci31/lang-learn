package hu.bme.aut.langlearn.presentation.deck_screen.main_deck_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.DeckRepository
import hu.bme.aut.langlearn.domain.Deck
import javax.inject.Inject

@HiltViewModel
class DeckViewModel @Inject constructor(
    repository: DeckRepository,
) : ViewModel() {

    val deckList = repository.getAllDecks()

    fun onDeckClick(deck: Deck) {
    }
}