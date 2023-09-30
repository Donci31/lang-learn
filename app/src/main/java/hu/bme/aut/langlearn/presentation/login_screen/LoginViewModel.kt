package hu.bme.aut.langlearn.presentation.login_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.AuthRepository
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateEmail(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    var loginState by mutableStateOf(LoginState())

    fun loginUser() {
        viewModelScope.launch {
            repository.loginUser(email, password).collectLatest { result ->
                loginState = when (result) {
                    is Resource.Success -> {
                        LoginState(isSuccess = "Success")
                    }

                    is Resource.Loading -> {
                        LoginState(isLoading = true)
                    }

                    is Resource.Error -> {
                        LoginState(isError = result.message)
                    }
                }
            }
        }
    }

    fun resetState() {
        loginState = LoginState()
    }
}