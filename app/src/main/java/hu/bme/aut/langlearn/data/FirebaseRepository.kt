package hu.bme.aut.langlearn.data

import hu.bme.aut.langlearn.data.deck_screen.Deck

interface FirebaseRepository {
    fun getAllDecks(): List<Deck>

    fun addDeck(deck: Deck)
}