package hu.bme.aut.langlearn.profile_screen

import hu.bme.aut.langlearn.domain.entities.Deck
import hu.bme.aut.langlearn.domain.entities.DeckPractice
import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.domain.repositories.DeckRepository
import hu.bme.aut.langlearn.domain.repositories.ProgressRepository
import hu.bme.aut.langlearn.domain.use_cases.GetAllDecksUseCase
import hu.bme.aut.langlearn.domain.use_cases.login_screen.LoginUserUseCase
import hu.bme.aut.langlearn.domain.use_cases.profile_screen.GetPracticedLanguagesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPracticedLanguagesUseCaseTest {

    private lateinit var mockProgressRepository: ProgressRepository
    private lateinit var getAllDecksUseCase: GetAllDecksUseCase
    private lateinit var getPracticedLanguagesUseCase: GetPracticedLanguagesUseCase

    @Before
    fun setUp() {
        mockProgressRepository = mockk()
        getAllDecksUseCase = mockk()
        getPracticedLanguagesUseCase =
            GetPracticedLanguagesUseCase(mockProgressRepository, getAllDecksUseCase)
    }

    @Test
    fun `invoke returns list of practiced languages`() = runTest {
        // Arrange
        val deckPractices = listOf(
            DeckPractice(deckId = "1", practices = emptyList()),
            DeckPractice(deckId = "2", practices = emptyList())
        )

        val decks = listOf(
            Deck(id = "1", name = "Deck 1", languageCode = "en", words = emptyList()),
            Deck(id = "2", name = "Deck 2", languageCode = "es", words = emptyList())
        )

        val expectedLanguages = listOf("English", "Spanish")

        coEvery { mockProgressRepository.getAllPractices() } returns flowOf(deckPractices)
        coEvery { getAllDecksUseCase() } returns flowOf(decks)

        // Act
        val result = getPracticedLanguagesUseCase().first()

        // Assert
        assertEquals(expectedLanguages, result)
    }

    @Test
    fun `invoke returns list of practiced languages with doubled language`() = runTest {
        // Arrange
        val deckPractices = listOf(
            DeckPractice(deckId = "1", practices = emptyList()),
            DeckPractice(deckId = "2", practices = emptyList()),
            DeckPractice(deckId = "3", practices = emptyList())
        )

        val decks = listOf(
            Deck(id = "1", name = "Deck 1", languageCode = "en", words = emptyList()),
            Deck(id = "2", name = "Deck 2", languageCode = "es", words = emptyList()),
            Deck(id = "3", name = "Deck 3", languageCode = "es", words = emptyList())
        )

        val expectedLanguages = listOf("English", "Spanish")

        coEvery { mockProgressRepository.getAllPractices() } returns flowOf(deckPractices)
        coEvery { getAllDecksUseCase() } returns flowOf(decks)

        // Act
        val result = getPracticedLanguagesUseCase().first()

        // Assert
        assertEquals(expectedLanguages, result)
    }

    @Test
    fun `invoke returns list of practiced languages with extra deck`() = runTest {
        // Arrange
        val deckPractices = listOf(
            DeckPractice(deckId = "1", practices = emptyList()),
            DeckPractice(deckId = "2", practices = emptyList())
        )

        val decks = listOf(
            Deck(id = "1", name = "Deck 1", languageCode = "en", words = emptyList()),
            Deck(id = "2", name = "Deck 2", languageCode = "es", words = emptyList()),
            Deck(id = "3", name = "Deck 3", languageCode = "de", words = emptyList())
        )

        val expectedLanguages = listOf("English", "Spanish")

        coEvery { mockProgressRepository.getAllPractices() } returns flowOf(deckPractices)
        coEvery { getAllDecksUseCase() } returns flowOf(decks)

        // Act
        val result = getPracticedLanguagesUseCase().first()

        // Assert
        assertEquals(expectedLanguages, result)
    }

    @Test
    fun `invoke returns list of practiced languages with extra practice`() = runTest {
        // Arrange
        val deckPractices = listOf(
            DeckPractice(deckId = "1", practices = emptyList()),
            DeckPractice(deckId = "2", practices = emptyList()),
            DeckPractice(deckId = "3", practices = emptyList())
        )

        val decks = listOf(
            Deck(id = "1", name = "Deck 1", languageCode = "en", words = emptyList()),
            Deck(id = "2", name = "Deck 2", languageCode = "es", words = emptyList())
        )

        val expectedLanguages = listOf("English", "Spanish")

        coEvery { mockProgressRepository.getAllPractices() } returns flowOf(deckPractices)
        coEvery { getAllDecksUseCase() } returns flowOf(decks)

        // Act
        val result = getPracticedLanguagesUseCase().first()

        // Assert
        assertEquals(expectedLanguages, result)
    }
}