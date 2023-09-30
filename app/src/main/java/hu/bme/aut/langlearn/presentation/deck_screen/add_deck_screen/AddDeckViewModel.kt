package hu.bme.aut.langlearn.presentation.deck_screen.add_deck_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.FirebaseRepository
import hu.bme.aut.langlearn.domain.Deck
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddDeckViewModel @Inject constructor(
    private val repository: FirebaseRepository
) : ViewModel() {
    var deckName by mutableStateOf("")
    val statefulWords = mutableStateListOf<StatefulWord>()

    fun addNewWord() {
        statefulWords.add(StatefulWord())
    }

    fun addNewDeck() {
        repository.addDeck(
            Deck(
                id  = UUID.randomUUID().toString(),
                name = deckName,
                words = statefulWords.map { it.toWord() }
            )
        )
    }
}