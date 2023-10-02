package hu.bme.aut.langlearn.presentation.practice_screen.main_practice_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.FirestoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PracticeScreenViewModel @Inject constructor(
    repository: FirestoreRepository
): ViewModel() {

    val deckNameList = repository.getDeckNames()
}