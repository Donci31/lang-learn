package hu.bme.aut.langlearn.data

import hu.bme.aut.langlearn.data.deck_screen.Deck
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    fun getAllDecks(): Flow<Resource<List<Deck>>>

    fun addDeck(deck: Deck)
}