package hu.bme.aut.langlearn.domain.navigation

import hu.bme.aut.langlearn.data.repositories.AuthRepository
import javax.inject.Inject

class IsUserLoggedInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(): Boolean = authRepository.currentUser != null
}