package hu.bme.aut.langlearn.domain.deck_screen

import hu.bme.aut.langlearn.data.repositories.DeckRepository
import hu.bme.aut.langlearn.data.repositories.ProgressRepository
import hu.bme.aut.langlearn.domain.entities.DeckWithPractice
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CombineDecksWithPracticesUseCase @Inject constructor(
    private val deckRepository: DeckRepository,
    private val progressRepository: ProgressRepository,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<List<DeckWithPractice>> =
        progressRepository.getAllPractices().flatMapLatest { deckPractices ->
            deckRepository.getAllDecks().map { decks ->
                val deckPracticesMap = deckPractices.associateBy { it.deckId }

                decks.map { deck ->
                    deckPracticesMap[deck.id]?.let { deckPractice ->
                        DeckWithPractice(
                            id = deck.id,
                            name = deck.name,
                            flagEmoji = getFlagEmoji(deck.languageCode),
                            words = deck.words,
                            practices = deckPractice.practices.takeLast(n = 5)
                        )
                    } ?: DeckWithPractice(
                        id = deck.id,
                        name = deck.name,
                        flagEmoji = getFlagEmoji(deck.languageCode),
                        words = deck.words,
                        practices = emptyList()
                    )
                }
            }
        }

    private fun getFlagEmoji(country: String): String =
        country.uppercase().map {
            String(Character.toChars(it.code + 127397))
        }.joinToString("")
}