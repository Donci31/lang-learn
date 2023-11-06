package hu.bme.aut.langlearn.practice_screen

import hu.bme.aut.langlearn.domain.repositories.SentenceRepository
import hu.bme.aut.langlearn.domain.use_cases.practice_screen.GetSentenceUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GetSentenceUseCaseTest {

    @Test
    fun `invoke returns sentence with word replaced by underscores`() = runTest {
        // Arrange
        val mockSentenceRepository = mockk<SentenceRepository>()
        val getSentenceUseCase = GetSentenceUseCase(mockSentenceRepository)
        val word = "example"
        val languageCode = "en"
        val sentence = "This is an example sentence with the word Example."
        val expectedResult = "This is an _____ sentence with the word _____."

        coEvery { mockSentenceRepository.getSentence(word, languageCode) } returns sentence

        // Act
        val result = getSentenceUseCase(word, languageCode)

        // Assert
        assertEquals(expectedResult, result)
    }
}