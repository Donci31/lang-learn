package hu.bme.aut.langlearn.presentation.profile_screen.main_profile_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.repositories.AuthRepository
import hu.bme.aut.langlearn.data.repositories.DeckRepository
import hu.bme.aut.langlearn.data.repositories.ProgressRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    progressRepository: ProgressRepository,
    private val deckRepository: DeckRepository,
) : ViewModel() {

    val username = authRepository.currentUser?.displayName

    val profilePicture = authRepository.currentUser?.photoUrl

    @OptIn(ExperimentalCoroutinesApi::class)
    var languages = progressRepository.getAllPractices()
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

    fun logout() {
        authRepository.logout()
    }
}