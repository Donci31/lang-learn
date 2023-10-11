package hu.bme.aut.langlearn.data

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>

    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>

    fun getCurrentUser() : FirebaseUser

    fun logout()
}