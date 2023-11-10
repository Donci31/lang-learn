package hu.bme.aut.langlearn.domain.use_cases.profile_screen

import hu.bme.aut.langlearn.domain.repositories.ProgressRepository
import hu.bme.aut.langlearn.domain.use_cases.GetAllDecksUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import java.util.Locale
import javax.inject.Inject

class GetPracticedLanguagesUseCase @Inject constructor(
    private val progressRepository: ProgressRepository,
    private val getAllDecksUseCase: GetAllDecksUseCase
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<List<String>> = progressRepository.getAllPractices()
        .flatMapLatest { deckPractices ->
            getAllDecksUseCase().map { decks ->
                val decksMap = decks.associateBy { it.id }

                deckPractices.mapNotNull { deckPractice ->
                    decksMap[deckPractice.deckId]?.languageCode?.let { languageCode ->
                        Locale(languageCode).displayLanguage
                    }
                }.distinct()
            }
        }
}