package hu.bme.aut.langlearn.data.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import hu.bme.aut.langlearn.presentation.singup_screen.Gender
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val currentUser: FirebaseUser?

    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>

    fun registerUser(
        userName: String,
        email: String,
        password: String,
        gender: Gender
    ): Flow<Resource<AuthResult>>

    fun logout()
}