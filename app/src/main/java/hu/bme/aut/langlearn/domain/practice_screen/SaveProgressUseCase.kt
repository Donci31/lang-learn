package hu.bme.aut.langlearn.domain.practice_screen

import hu.bme.aut.langlearn.data.repositories.ProgressRepository
import hu.bme.aut.langlearn.domain.entities.Practice
import java.util.Date
import javax.inject.Inject

class SaveProgressUseCase @Inject constructor(
    private val progressRepository: ProgressRepository,
) {

    operator fun invoke(deckId: String, correctAnswerNumber: Int, cardListSize: Int) {
        progressRepository.addPractice(
            deckId = deckId,
            practice = Practice(
                date = Date(),
                score = correctAnswerNumber.toDouble() / cardListSize
            )
        )
    }
}