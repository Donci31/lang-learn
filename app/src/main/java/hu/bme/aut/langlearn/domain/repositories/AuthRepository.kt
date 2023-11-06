package hu.bme.aut.langlearn.domain.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    val currentUser: FirebaseUser?

    suspend fun loginUser(email: String, password: String): AuthResult

    suspend fun registerUser(email: String, password: String): AuthResult

    fun logout()
}