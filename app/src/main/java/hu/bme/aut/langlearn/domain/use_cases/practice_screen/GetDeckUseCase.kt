package hu.bme.aut.langlearn.domain.use_cases.practice_screen

import hu.bme.aut.langlearn.domain.repositories.DeckRepository
import javax.inject.Inject

class GetDeckUseCase @Inject constructor(
    private val deckRepository: DeckRepository
) {

    suspend operator fun invoke(deckId: String) = deckRepository.getDeck(deckId)
}