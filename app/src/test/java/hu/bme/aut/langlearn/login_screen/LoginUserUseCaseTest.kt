package hu.bme.aut.langlearn.login_screen

import com.google.firebase.auth.AuthResult
import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.domain.use_cases.login_screen.LoginUserUseCase
import hu.bme.aut.langlearn.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginUserUseCaseTest {

    @Test
    fun `invoke returns the correct result`() = runTest {
        // Arrange
        val mockAuthRepository = mockk<AuthRepository>()
        val loginUserUseCase = LoginUserUseCase(mockAuthRepository)
        val email = "test@example.com"
        val password = "password"
        val expectedResult = Resource.Success(mockk<AuthResult>())

        coEvery { mockAuthRepository.loginUser(email, password) } returns flowOf(expectedResult)

        // Act
        val result = loginUserUseCase(email, password)

        // Assert
        result.collect { actualResult ->
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `invoke returns error result for incorrect email`() = runTest {
        // Arrange
        val mockAuthRepository = mockk<AuthRepository>()
        val loginUserUseCase = LoginUserUseCase(mockAuthRepository)
        val incorrectEmail = "incorrect@example.com"
        val password = "password"
        val expectedResult = Resource.Error(data = mockk<AuthResult>(), message = "Wrong email")

        coEvery { mockAuthRepository.loginUser(incorrectEmail, password) } returns flowOf(
            expectedResult
        )

        // Act
        val result = loginUserUseCase(incorrectEmail, password)

        // Assert
        result.collect { actualResult ->
            assertEquals(expectedResult, actualResult)
        }
    }
}