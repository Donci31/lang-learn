package hu.bme.aut.langlearn.deck_screen

import hu.bme.aut.langlearn.domain.entities.Deck
import hu.bme.aut.langlearn.domain.entities.DeckPractice
import hu.bme.aut.langlearn.domain.entities.DeckWithPractice
import hu.bme.aut.langlearn.domain.entities.Practice
import hu.bme.aut.langlearn.domain.entities.Word
import hu.bme.aut.langlearn.domain.repositories.ProgressRepository
import hu.bme.aut.langlearn.domain.use_cases.GetAllDecksUseCase
import hu.bme.aut.langlearn.domain.use_cases.deck_screen.CombineDecksWithPracticesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

@ExperimentalCoroutinesApi
class CombineDecksWithPracticesUseCaseTest {

    @Test
    fun `invoke returns list of DeckWithPractice`() = runTest {
        // Arrange
        val mockGetAllDecksUseCase = mockk<GetAllDecksUseCase>()
        val mockProgressRepository = mockk<ProgressRepository>()
        val combineDecksWithPracticesUseCase =
            CombineDecksWithPracticesUseCase(mockGetAllDecksUseCase, mockProgressRepository)

        val date1 = Date()
        val date2 = Date()

        val deckPractices = listOf(
            DeckPractice(
                deckId = "1",
                practices = listOf(
                    Practice(
                        date = date1,
                        score = 4.5
                    )
                )
            ),
            DeckPractice(
                deckId = "2",
                practices = listOf(
                    Practice(
                        date = date2,
                        score = 3.0
                    )
                )
            )
        )

        val decks = listOf(
            Deck(
                id = "1",
                name = "Deck 1",
                private = false,
                hasAccess = emptyList(),
                languageCode = "de",
                words = listOf(
                    Word("Hallo", "Hello"),
                    Word("Auf Wiedersehen", "Goodbye")
                )
            ),
            Deck(
                id = "2",
                name = "Deck 2",
                private = false,
                hasAccess = emptyList(),
                languageCode = "es",
                words = listOf(
                    Word("Hola", "Hello"),
                    Word("Adiós", "Goodbye")
                )
            )
        )

        val expectedList = listOf(
            DeckWithPractice(
                id = "1",
                name = "Deck 1",
                private = false,
                hasAccess = emptyList(),
                flagEmoji = "\uD83C\uDDE9\uD83C\uDDEA",
                words = listOf(
                    Word("Hallo", "Hello"),
                    Word("Auf Wiedersehen", "Goodbye")
                ),
                practices = listOf(
                    Practice(
                        date = date1,
                        score = 4.5
                    )
                )
            ),
            DeckWithPractice(
                id = "2",
                name = "Deck 2",
                private = false,
                hasAccess = emptyList(),
                flagEmoji = "\uD83C\uDDEA\uD83C\uDDF8",
                words = listOf(
                    Word("Hola", "Hello"),
                    Word("Adiós", "Goodbye")
                ),
                practices = listOf(
                    Practice(
                        date = date2,
                        score = 3.0
                    )
                )
            )
        )

        coEvery { mockProgressRepository.getAllPractices() } returns flowOf(deckPractices)
        coEvery { mockGetAllDecksUseCase() } returns flowOf(decks)

        // Act
        val result = combineDecksWithPracticesUseCase().first()

        // Assert
        assertEquals(result, expectedList)
    }
}