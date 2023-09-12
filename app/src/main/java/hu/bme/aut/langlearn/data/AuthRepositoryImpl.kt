package hu.bme.aut.langlearn.data

import com.google.firebase.auth.FirebaseAuth
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {
    override fun loginUser(email: String, password: String) = flow {
        emit(Resource.Loading())

        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()

        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }.flowOn(Dispatchers.IO)

    override fun registerUser(email: String, password: String) = flow {
        emit(Resource.Loading())

        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(message = it.message.toString()))
    }.flowOn(Dispatchers.IO)
}