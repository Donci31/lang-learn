package hu.bme.aut.langlearn.presentation.practice_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.FirestoreRepository
import hu.bme.aut.langlearn.domain.Deck
import hu.bme.aut.langlearn.domain.Word
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: FirestoreRepository
) : ViewModel() {
    private val deckId: String = checkNotNull(savedStateHandle["deckId"])

    var currentCardIndex by mutableIntStateOf(0)

    var deck: Deck? = null

    init {
        viewModelScope.launch {
            deck = repository.getDeck(deckId)
        }
    }
}