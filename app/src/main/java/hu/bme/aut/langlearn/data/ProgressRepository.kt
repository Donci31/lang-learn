package hu.bme.aut.langlearn.data

import hu.bme.aut.langlearn.domain.DeckPractice
import hu.bme.aut.langlearn.domain.Practice
import kotlinx.coroutines.flow.Flow

interface ProgressRepository {
    fun getAllPractices(): Flow<List<DeckPractice>>

    fun createDeckPractice(deckId: String)

    fun addPractice(practice: Practice, deckId: String)
}