package hu.bme.aut.langlearn.profile_screen

import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.domain.use_cases.profile_screen.LogoutUseCase
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class LogoutUseCaseTest {

    @Test
    fun `invoke calls logout on authRepository`() {
        // Arrange
        val authRepository = mockk<AuthRepository>(relaxed = true)
        val logoutUseCase = LogoutUseCase(authRepository)

        // Act
        logoutUseCase()

        // Assert
        verify(exactly = 1) {
            authRepository.logout()
        }
    }
}