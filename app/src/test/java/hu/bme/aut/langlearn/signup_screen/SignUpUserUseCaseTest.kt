package hu.bme.aut.langlearn.signup_screen

import com.google.firebase.auth.AuthResult
import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.domain.use_cases.signup_screen.SignUpUserUseCase
import hu.bme.aut.langlearn.presentation.singup_screen.Gender
import hu.bme.aut.langlearn.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class SignUpUserUseCaseTest {

    @Test
    fun `invoke returns the correct result`() = runTest {
        // Arrange
        val mockAuthRepository = mockk<AuthRepository>()
        val signUpUserUseCase = SignUpUserUseCase(mockAuthRepository)
        val userName = "John Doe"
        val email = "john.doe@example.com"
        val password = "password"
        val gender = Gender.MALE
        val expectedResult = Resource.Success(mockk<AuthResult>())

        coEvery {
            mockAuthRepository.registerUser(userName, email, password, gender)
        } returns flowOf(expectedResult)

        // Act
        val result = signUpUserUseCase(userName, email, password, gender)

        // Assert
        result.collect { actualResult ->
            assertEquals(expectedResult, actualResult)
        }
    }
}