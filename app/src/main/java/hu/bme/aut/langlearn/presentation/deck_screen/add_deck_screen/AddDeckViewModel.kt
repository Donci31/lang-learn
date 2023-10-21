package hu.bme.aut.langlearn.presentation.deck_screen.add_deck_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.mlkit.nl.languageid.LanguageIdentifier
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.repositories.DeckRepository
import hu.bme.aut.langlearn.domain.Deck
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddDeckViewModel @Inject constructor(
    private val repository: DeckRepository,
    private val languageIdentifier: LanguageIdentifier,
) : ViewModel() {
    var deckName by mutableStateOf("")
    val statefulWords = mutableStateListOf<StatefulWord>()

    fun addNewWord() {
        statefulWords.add(StatefulWord())
    }

    fun addNewDeck() {
        getDeckLanguage { languageCode ->
            repository.addDeck(
                Deck(
                    id = UUID.randomUUID().toString(),
                    name = deckName,
                    languageCode = languageCode,
                    words = statefulWords.map { it.toWord() }
                )
            )
        }
    }

    private fun getDeckLanguage(callback: (String) -> Unit) {
        languageIdentifier.identifyLanguage(statefulWords.first().foreignWord.value)
            .addOnSuccessListener { languageCode ->
                callback(languageCode)
            }
    }
}