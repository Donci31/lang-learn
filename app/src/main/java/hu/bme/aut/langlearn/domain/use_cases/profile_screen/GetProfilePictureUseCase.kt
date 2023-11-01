package hu.bme.aut.langlearn.domain.use_cases.profile_screen

import android.net.Uri
import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import javax.inject.Inject

class GetProfilePictureUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(): Uri = authRepository.currentUser?.photoUrl!!
}