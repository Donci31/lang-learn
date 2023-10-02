package hu.bme.aut.langlearn.data

import hu.bme.aut.langlearn.domain.Deck
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    fun getAllDecks(): Flow<List<Deck>>

    fun getDeckNames(): Flow<List<String>>

    fun addDeck(deck: Deck)
}