package hu.bme.aut.langlearn.profile_screen

import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.domain.use_cases.profile_screen.GetUsernameUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUsernameUseCaseTest {

    @Test
    fun `invoke returns user's display name when user is logged in`() {
        // Arrange
        val authRepository = mockk<AuthRepository>()
        val displayNameExample = "John Doe"
        every { authRepository.currentUser } returns mockk {
            every { displayName } returns displayNameExample
        }

        val getUsernameUseCase = GetUsernameUseCase(authRepository)

        // Act
        val result = getUsernameUseCase()

        // Assert
        assertEquals(displayNameExample, result)
    }

    @Test(expected = NullPointerException::class)
    fun `invoke throws exception when user is not logged in`() {
        // Arrange
        val authRepository = mockk<AuthRepository>()
        every { authRepository.currentUser } returns null

        val getUsernameUseCase = GetUsernameUseCase(authRepository)

        // Act & Assert
        getUsernameUseCase()
    }
}