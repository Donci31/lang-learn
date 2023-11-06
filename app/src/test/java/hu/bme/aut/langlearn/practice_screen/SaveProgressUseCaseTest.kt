package hu.bme.aut.langlearn.practice_screen

import hu.bme.aut.langlearn.domain.entities.Practice
import hu.bme.aut.langlearn.domain.repositories.ProgressRepository
import hu.bme.aut.langlearn.domain.use_cases.practice_screen.SaveProgressUseCase
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.Date

@ExperimentalCoroutinesApi
class SaveProgressUseCaseTest {

    @Test
    fun `invoke adds practice to progress repository with correct parameters`() = runTest {
        // Arrange
        val mockProgressRepository = mockk<ProgressRepository>(relaxed = true)
        val saveProgressUseCase = SaveProgressUseCase(mockProgressRepository)
        val deckId = "deck1"
        val correctAnswerNumber = 3
        val cardListSize = 5

        val expectedPractice = Practice(
            date = Date(),
            score = 0.6
        )

        // Act
        saveProgressUseCase(deckId, correctAnswerNumber, cardListSize)

        // Assert
        verify {
            mockProgressRepository.addPractice(deckId, expectedPractice)
        }
    }
}