package hu.bme.aut.langlearn.presentation.deck_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.deck_screen.Deck
import javax.inject.Inject

@HiltViewModel
class DeckViewModel @Inject constructor(

): ViewModel() {
    val decks = listOf<Deck>(Deck("s", "Germanic", listOf()))

    fun onDeckClick(deck: Deck) {

    }

    fun onAddDeckClick() {

    }
}