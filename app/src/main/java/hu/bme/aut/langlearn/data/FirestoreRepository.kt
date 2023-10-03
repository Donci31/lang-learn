package hu.bme.aut.langlearn.data

import hu.bme.aut.langlearn.domain.Deck
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    fun getAllDecks(): Flow<List<Deck>>

    suspend fun getDeck(deckId: String): Deck?

    fun addDeck(deck: Deck)
}