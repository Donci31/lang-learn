package hu.bme.aut.langlearn.presentation.add_deck_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import hu.bme.aut.langlearn.domain.Word

data class StatefulWord(
    var foreignWord: MutableState<String> = mutableStateOf(""),
    val englishTranslation: MutableState<String> = mutableStateOf(""),
) {
    fun toWord(): Word = Word(
        foreignWord = foreignWord.value,
        englishTranslation = englishTranslation.value
    )
}
