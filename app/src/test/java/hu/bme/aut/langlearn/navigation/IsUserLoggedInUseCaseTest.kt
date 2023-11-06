package hu.bme.aut.langlearn.navigation

import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.domain.use_cases.navigation.IsUserLoggedInUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class IsUserLoggedInUseCaseTest {

    @Test
    fun `invoke returns true when there is a current user`() {
        // Arrange
        val authRepository = mockk<AuthRepository>()
        every { authRepository.currentUser } returns mockk()
        val isUserLoggedInUseCase = IsUserLoggedInUseCase(authRepository)

        // Act
        val result = isUserLoggedInUseCase()

        // Assert
        assertTrue(result)
    }

    @Test
    fun `invoke returns false when there is no current user`() {
        // Arrange
        val authRepository = mockk<AuthRepository>()
        every { authRepository.currentUser } returns null
        val isUserLoggedInUseCase = IsUserLoggedInUseCase(authRepository)

        // Act
        val result = isUserLoggedInUseCase()

        // Assert
        assertFalse(result)
    }
}