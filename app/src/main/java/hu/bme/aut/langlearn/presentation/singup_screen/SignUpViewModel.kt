package hu.bme.aut.langlearn.presentation.singup_screen

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
class SignUpViewModel @Inject constructor(
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

    var signUpState by mutableStateOf(SignUpState())

    fun signUpUser() {
        viewModelScope.launch {
            repository.registerUser(email, password).collectLatest { result ->
                signUpState = when (result) {
                    is Resource.Success -> {
                         SignUpState(isSuccess = "Success")
                    }

                    is Resource.Loading -> {
                        SignUpState(isLoading = true)
                    }

                    is Resource.Error -> {
                        SignUpState(isError = result.message)
                    }
                }
            }
        }
    }

    fun resetState() {
        signUpState = SignUpState()
    }
}