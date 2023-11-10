package hu.bme.aut.langlearn.domain.use_cases

import hu.bme.aut.langlearn.domain.entities.Deck
import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.domain.repositories.DeckRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllDecksUseCase @Inject constructor(
    private val deckRepository: DeckRepository,
    private val authRepository: AuthRepository,
) {

    operator fun invoke(): Flow<List<Deck>> =
        deckRepository
            .getAllDecks()
            .map { deckList ->
                deckList.filter { deck ->
                    !deck.private || deck.hasAccess.contains(authRepository.currentUser!!.uid)
                }
            }
}