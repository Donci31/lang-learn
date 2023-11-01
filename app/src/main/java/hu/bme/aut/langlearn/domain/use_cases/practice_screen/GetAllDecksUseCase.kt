package hu.bme.aut.langlearn.domain.use_cases.practice_screen

import hu.bme.aut.langlearn.domain.repositories.DeckRepository
import javax.inject.Inject

class GetAllDecksUseCase @Inject constructor(
    private val deckRepository: DeckRepository
) {

    operator fun invoke() = deckRepository.getAllDecks()
}