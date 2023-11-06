package hu.bme.aut.langlearn.login_screen

import com.google.firebase.auth.AuthResult
import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.domain.use_cases.login_screen.LoginUserUseCase
import hu.bme.aut.langlearn.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginUserUseCaseTest {

    private lateinit var authRepository: AuthRepository
    private lateinit var loginUserUseCase: LoginUserUseCase

    @Before
    fun setUp() {
        authRepository = mockk()
        loginUserUseCase = LoginUserUseCase(authRepository)
    }

    @Test
    fun `invoke returns loading, success, or error Resource based on authRepository result`() = runTest {
        // Arrange
        val email = "test@example.com"
        val password = "password"
        val authResult = mockk<AuthResult>()

        coEvery { authRepository.loginUser(email, password) } returns authResult

        // Act
        val result = loginUserUseCase(email, password).toList()

        // Assert
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
    }

    @Test
    fun `invoke returns Resource Error when an exception occurs`() = runTest {
        // Arrange
        val email = "test@example.com"
        val password = "password"
        val errorMessage = "Invalid credentials"

        coEvery { authRepository.loginUser(email, password) } throws Exception(errorMessage)

        // Act
        val result = loginUserUseCase(email, password).toList()

        // Assert
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
    }
}