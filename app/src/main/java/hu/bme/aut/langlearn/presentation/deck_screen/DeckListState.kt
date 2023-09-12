package hu.bme.aut.langlearn.presentation.deck_screen

import hu.bme.aut.langlearn.data.deck_screen.Deck

data class DeckListState(
    val decks: List<Deck> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)