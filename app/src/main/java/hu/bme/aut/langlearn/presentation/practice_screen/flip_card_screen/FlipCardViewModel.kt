package hu.bme.aut.langlearn.presentation.practice_screen.flip_card_screen

import hu.bme.aut.langlearn.presentation.practice_screen.PracticeViewModel

class FlipCardViewModel : PracticeViewModel() {
    fun goToPreviousCard() {
        if (currentCardIndex > 0) {
            currentCardIndex--
        }
    }

    fun isNotFirstWord(): Boolean = currentCardIndex > 0
}