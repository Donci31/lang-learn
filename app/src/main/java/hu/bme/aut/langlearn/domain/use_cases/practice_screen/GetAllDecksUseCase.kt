package hu.bme.aut.langlearn.domain.use_cases.practice_screen

import hu.bme.aut.langlearn.domain.entities.Deck
import hu.bme.aut.langlearn.domain.repositories.DeckRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDecksUseCase @Inject constructor(
    private val deckRepository: DeckRepository
) {

    operator fun invoke(): Flow<List<Deck>> = deckRepository.getAllDecks()
}