package hu.bme.aut.langlearn.domain.use_cases.profile_screen

import hu.bme.aut.langlearn.domain.repositories.DeckRepository
import hu.bme.aut.langlearn.domain.repositories.ProgressRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import java.util.Locale
import javax.inject.Inject

class GetPracticedLanguagesUseCase @Inject constructor(
    private val progressRepository: ProgressRepository,
    private val deckRepository: DeckRepository
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<List<String>> = progressRepository.getAllPractices()
        .flatMapLatest { deckPractices ->
            deckRepository.getAllDecks().map { decks ->
                val decksMap = decks.associateBy { it.id }

                deckPractices.mapNotNull { deckPractice ->
                    decksMap[deckPractice.deckId]?.languageCode?.let { languageCode ->
                        Locale(languageCode).displayLanguage
                    }
                }.distinct()
            }
        }
}