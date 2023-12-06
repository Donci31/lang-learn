package hu.bme.aut.langlearn.domain.use_cases.login_screen

import com.google.firebase.auth.AuthResult
import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    operator fun invoke(email: String, password: String):
            Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())

        val result = authRepository.loginUser(email, password)

        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }.flowOn(Dispatchers.IO)
}