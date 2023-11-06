package hu.bme.aut.langlearn.profile_screen

import android.net.Uri
import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.domain.use_cases.profile_screen.GetProfilePictureUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class GetProfilePictureUseCaseTest {

    @Test
    fun `invoke returns user's profile picture Uri when user is logged in`() {
        // Arrange
        val authRepository = mockk<AuthRepository>()

        val userPhotoUrl = mockk<Uri>()

        every { authRepository.currentUser } returns mockk {
            every { photoUrl } returns userPhotoUrl
        }

        val getProfilePictureUseCase = GetProfilePictureUseCase(authRepository)

        // Act
        val result = getProfilePictureUseCase()

        // Assert
        assertEquals(userPhotoUrl, result)
    }

    @Test(expected = NullPointerException::class)
    fun `invoke throws exception when user is not logged in`() {
        // Arrange
        val authRepository = mockk<AuthRepository>()
        every { authRepository.currentUser } returns null

        val getProfilePictureUseCase = GetProfilePictureUseCase(authRepository)

        // Act & Assert
        getProfilePictureUseCase()
    }
}