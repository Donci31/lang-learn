package hu.bme.aut.langlearn.presentation.practice_screen.sentence_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.repositories.DeckRepository
import hu.bme.aut.langlearn.data.repositories.ProgressRepository
import hu.bme.aut.langlearn.data.repositories.SentenceRepository
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SentenceViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    deckRepository: DeckRepository,
    private val progressRepository: ProgressRepository,
    private val sentenceRepository: SentenceRepository,
) : PracticeViewModel(
    savedStateHandle = savedStateHandle,
    repository = deckRepository
) {
    var curSentence by mutableStateOf("")

    init {
        viewModelScope.launch {
            curSentence = sentenceRepository.getSentence("Dog")
        }
    }
}