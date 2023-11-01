package hu.bme.aut.langlearn.domain.signup_screen

import com.google.firebase.auth.AuthResult
import hu.bme.aut.langlearn.data.repositories.AuthRepository
import hu.bme.aut.langlearn.presentation.singup_screen.Gender
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    operator fun invoke(
        userName: String,
        email: String,
        password: String,
        gender: Gender,
    ): Flow<Resource<AuthResult>> =
        authRepository.registerUser(userName, email, password, gender)
}