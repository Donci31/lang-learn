package hu.bme.aut.langlearn.data

import hu.bme.aut.langlearn.domain.Deck
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    fun getAllDecks(): Flow<List<Deck>>

    fun addDeck(deck: Deck)
}