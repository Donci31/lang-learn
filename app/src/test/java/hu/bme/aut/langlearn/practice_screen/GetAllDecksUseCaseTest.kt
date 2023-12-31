package hu.bme.aut.langlearn.practice_screen

import hu.bme.aut.langlearn.domain.entities.Deck
import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.domain.repositories.DeckRepository
import hu.bme.aut.langlearn.domain.use_cases.GetAllDecksUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAllDecksUseCaseTest {

    @Test
    fun `invoke returns list of decks`() = runTest {
        // Arrange
        val deckRepository = mockk<DeckRepository>()
        val authRepository = mockk<AuthRepository>()
        val expectedDecks = listOf(
            Deck(id = "1", name = "Deck 1"),
            Deck(id = "2", name = "Deck 2")
        )
        coEvery { deckRepository.getAllDecks() } returns flowOf(expectedDecks)
        val getAllDecksUseCase = GetAllDecksUseCase(deckRepository, authRepository)

        // Act
        val result = getAllDecksUseCase()

        // Assert
        assertEquals(expectedDecks, result.first())
    }
}