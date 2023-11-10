package hu.bme.aut.langlearn.presentation.deck_screen.add_deck_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.domain.use_cases.deck_screen.AddDeckUseCase
import javax.inject.Inject

@HiltViewModel
class AddDeckViewModel @Inject constructor(
    private val addDeckUseCase: AddDeckUseCase
) : ViewModel() {

    var deckName by mutableStateOf("")
    var isPrivate by mutableStateOf(false)
    val statefulWords = mutableStateListOf<StatefulWord>()

    fun addNewWord() {
        statefulWords.add(StatefulWord())
    }

    fun addNewDeck() {
        addDeckUseCase(
            deckName = deckName,
            isPrivate = isPrivate,
            statefulWords = statefulWords
        )
    }
}