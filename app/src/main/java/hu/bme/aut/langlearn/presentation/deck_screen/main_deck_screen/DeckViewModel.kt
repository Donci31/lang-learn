package hu.bme.aut.langlearn.presentation.deck_screen.main_deck_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.DeckRepository
import hu.bme.aut.langlearn.data.ProgressRepository
import hu.bme.aut.langlearn.domain.DeckWithPractice
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DeckViewModel @Inject constructor(
    deckRepository: DeckRepository,
    progressRepository: ProgressRepository
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
                            words = deck.words,
                            practices = deckPractice.practices.takeLast(n = 5)
                        )
                    } ?: DeckWithPractice(deck.id, deck.name, deck.words, emptyList())
                }
            }
        }
}