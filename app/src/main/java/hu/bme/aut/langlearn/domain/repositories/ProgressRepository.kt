package hu.bme.aut.langlearn.domain.repositories

import hu.bme.aut.langlearn.domain.entities.DeckPractice
import hu.bme.aut.langlearn.domain.entities.Practice
import kotlinx.coroutines.flow.Flow

interface ProgressRepository {

    fun addNewUser(userId: String)

    fun getAllPractices(): Flow<List<DeckPractice>>

    fun addPractice(deckId: String, practice: Practice)
}