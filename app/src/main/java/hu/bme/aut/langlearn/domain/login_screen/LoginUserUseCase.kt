package hu.bme.aut.langlearn.domain.login_screen

import com.google.firebase.auth.AuthResult
import hu.bme.aut.langlearn.data.repositories.AuthRepository
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(email: String, password: String): Flow<Resource<AuthResult>> =
        authRepository.loginUser(email, password)
}