package hu.bme.aut.langlearn.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.repositories.AuthRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    fun isLoggedIn(): Boolean = repository.currentUser != null
}