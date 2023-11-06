package hu.bme.aut.langlearn.practice_screen

import hu.bme.aut.langlearn.domain.entities.Deck
import hu.bme.aut.langlearn.domain.repositories.DeckRepository
import hu.bme.aut.langlearn.domain.use_cases.practice_screen.GetDeckUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetDeckUseCaseTest {

    @Test
    fun `invoke returns deck`() = runTest {
        // Arrange
        val deckRepository = mockk<DeckRepository>()
        val deckId = "1"
        val expectedDeck = Deck(id = deckId, name = "Deck 1") // Provide the expected deck
        coEvery { deckRepository.getDeck(deckId) } returns expectedDeck
        val getDeckUseCase = GetDeckUseCase(deckRepository)

        // Act
        val result = getDeckUseCase(deckId)

        // Assert
        assertEquals(expectedDeck, result)
    }

    @Test
    fun `invoke returns null when deck is not found`() = runTest {
        // Arrange
        val deckRepository = mockk<DeckRepository>()
        val deckId = "2" // Assuming this deck ID is not in the repository
        coEvery { deckRepository.getDeck(deckId) } returns null
        val getDeckUseCase = GetDeckUseCase(deckRepository)

        // Act
        val result = getDeckUseCase(deckId)

        // Assert
        assertEquals(null, result)
    }
}