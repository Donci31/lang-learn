package hu.bme.aut.langlearn.data.repositories

import hu.bme.aut.langlearn.domain.entities.DeckPractice
import hu.bme.aut.langlearn.domain.entities.Practice
import kotlinx.coroutines.flow.Flow

interface ProgressRepository {
    fun getAllPractices(): Flow<List<DeckPractice>>

    fun addPractice(deckId: String, practice: Practice)
}