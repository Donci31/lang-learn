package hu.bme.aut.langlearn.domain.use_cases.profile_screen

import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke() {
        authRepository.logout()
    }
}