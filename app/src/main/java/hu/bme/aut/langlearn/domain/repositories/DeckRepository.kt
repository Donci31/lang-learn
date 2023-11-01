package hu.bme.aut.langlearn.domain.repositories

import hu.bme.aut.langlearn.domain.entities.Deck
import kotlinx.coroutines.flow.Flow

interface DeckRepository {
    fun getAllDecks(): Flow<List<Deck>>

    suspend fun getDeck(deckId: String): Deck?

    fun addDeck(deck: Deck)
}