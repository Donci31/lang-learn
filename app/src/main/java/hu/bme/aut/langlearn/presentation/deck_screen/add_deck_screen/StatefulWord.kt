package hu.bme.aut.langlearn.presentation.deck_screen.add_deck_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import hu.bme.aut.langlearn.domain.entities.Word
import java.util.UUID

data class StatefulWord(
    var foreignWord: MutableState<String> = mutableStateOf(""),
    val englishTranslation: MutableState<String> = mutableStateOf(""),
) {
    fun toWord(): Word = Word(
        id = UUID.randomUUID().toString(),
        foreignWord = foreignWord.value,
        englishTranslation = englishTranslation.value
    )
}
