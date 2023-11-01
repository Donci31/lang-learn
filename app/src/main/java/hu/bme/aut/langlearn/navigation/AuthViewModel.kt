package hu.bme.aut.langlearn.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.domain.use_cases.navigation.IsUserLoggedInUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase
) : ViewModel() {

    fun isLoggedIn(): Boolean = isUserLoggedInUseCase()
}