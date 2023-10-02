package hu.bme.aut.langlearn.data

import hu.bme.aut.langlearn.domain.Deck
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {
    fun getAllDecks(): Flow<List<Deck>>

    fun getDeck(deckId: String): Flow<Deck?>

    fun getDeckIdsAndNames(): Flow<List<Pair<String, String>>>

    fun addDeck(deck: Deck)
}