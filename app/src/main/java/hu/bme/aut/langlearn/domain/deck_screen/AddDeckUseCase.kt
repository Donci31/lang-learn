package hu.bme.aut.langlearn.domain.deck_screen

import com.google.mlkit.nl.languageid.LanguageIdentifier
import hu.bme.aut.langlearn.data.repositories.DeckRepository
import hu.bme.aut.langlearn.domain.entities.Deck
import hu.bme.aut.langlearn.presentation.deck_screen.add_deck_screen.StatefulWord
import java.util.UUID
import javax.inject.Inject

class AddDeckUseCase @Inject constructor(
    private val repository: DeckRepository,
    private val languageIdentifier: LanguageIdentifier
) {

    operator fun invoke(deckName: String, statefulWords: List<StatefulWord>) {
        getDeckLanguage(statefulWords) { languageCode ->
            val deck = Deck(
                id = UUID.randomUUID().toString(),
                name = deckName,
                languageCode = languageCode,
                words = statefulWords.map { it.toWord() }
            )

            repository.addDeck(deck)
        }
    }

    private fun getDeckLanguage(statefulWords: List<StatefulWord>, callback: (String) -> Unit) {
        languageIdentifier.identifyLanguage(
            statefulWords.joinToString(", ") { word ->
                word.foreignWord.value
            }
        )
            .addOnSuccessListener { languageCode ->
                callback(languageCode)
            }
    }
}