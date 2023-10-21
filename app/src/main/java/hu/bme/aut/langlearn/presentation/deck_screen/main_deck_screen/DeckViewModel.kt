package hu.bme.aut.langlearn.presentation.deck_screen.main_deck_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.repositories.DeckRepository
import hu.bme.aut.langlearn.data.repositories.ProgressRepository
import hu.bme.aut.langlearn.domain.DeckWithPractice
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DeckViewModel @Inject constructor(
    deckRepository: DeckRepository,
    progressRepository: ProgressRepository,
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val combinedList = progressRepository.getAllPractices()
        .flatMapLatest { deckPractices ->
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