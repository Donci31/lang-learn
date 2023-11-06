package hu.bme.aut.langlearn.domain.use_cases.signup_screen

import android.net.Uri
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.presentation.singup_screen.Gender
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val firestore: FirebaseFirestore,
) {

    operator fun invoke(
        userName: String,
        email: String,
        password: String,
        gender: Gender,
    ): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())

        val result = authRepository.registerUser(email, password)

        val user = result.user!!

        user.updateProfile(
            userProfileChangeRequest {
                displayName = userName
                photoUri = if (gender == Gender.MALE) {
                    Uri.parse("https://www.shareicon.net/data/512x512/2016/05/24/770117_people_512x512.png")
                } else {
                    Uri.parse("https://www.shareicon.net/data/512x512/2016/05/24/770116_people_512x512.png")
                }
            }
        ).await()

        firestore
            .collection("users")
            .document(user.uid)
            .set(HashMap<String, Any>())

        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }.flowOn(Dispatchers.IO)
}