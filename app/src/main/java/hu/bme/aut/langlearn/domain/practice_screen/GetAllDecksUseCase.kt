package hu.bme.aut.langlearn.domain.practice_screen

import hu.bme.aut.langlearn.data.repositories.DeckRepository
import javax.inject.Inject

class GetAllDecksUseCase @Inject constructor(
    private val deckRepository: DeckRepository
) {

    operator fun invoke() = deckRepository.getAllDecks()
}