package hu.bme.aut.langlearn.data

import hu.bme.aut.langlearn.domain.DeckPractice
import kotlinx.coroutines.flow.Flow

interface ProgressRepository {
    fun getAllPractices(): Flow<List<DeckPractice>>
}