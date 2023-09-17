package hu.bme.aut.langlearn.presentation.deck_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.FirebaseRepository
import hu.bme.aut.langlearn.domain.Deck
import hu.bme.aut.langlearn.domain.Word
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DeckViewModel @Inject constructor(
    private val repository: FirebaseRepository,
) : ViewModel() {

    private val _deckListState = MutableStateFlow(DeckListState())
    val deckListState = _deckListState.asStateFlow()

    init {
        loadDeckList()
    }

    fun loadDeckList() {
        repository.getAllDecks().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _deckListState.value = result.data?.let {
                        DeckListState(decks = it)
                    } ?: DeckListState()
                }

                is Resource.Loading -> {
                    _deckListState.value = DeckListState(isLoading = true)
                }

                is Resource.Error -> {
                    _deckListState.value = DeckListState(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onDeckClick(deck: Deck) {

    }
}