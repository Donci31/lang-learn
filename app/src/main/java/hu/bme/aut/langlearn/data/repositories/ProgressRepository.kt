package hu.bme.aut.langlearn.data.repositories

import hu.bme.aut.langlearn.domain.DeckPractice
import hu.bme.aut.langlearn.domain.Practice
import kotlinx.coroutines.flow.Flow

interface ProgressRepository {
    fun getAllPractices(): Flow<List<DeckPractice>>

    fun addPractice(deckId: String, practice: Practice)
}