package hu.bme.aut.langlearn.presentation.deck_screen.add_deck_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WordItem(
    word: StatefulWord
) {
    Column {
        OutlinedTextField(
            value = word.foreignWord.value,
            onValueChange = { word.foreignWord.value = it },
            label = { Text("foreignWord") },
        )

        OutlinedTextField(
            value = word.englishTranslation.value,
            onValueChange = { word.englishTranslation.value = it },
            label = { Text("englishTranslation") },
        )
    }
}