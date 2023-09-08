package hu.bme.aut.langlearn.presentation.singup_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.AuthRepository
import hu.bme.aut.langlearn.presentation.login_screen.LoginState
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _signUpState = Channel<LoginState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun signUpUser(email: String, password: String) = viewModelScope.launch {
        repository.registerUser(email, password).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signUpState.send(LoginState(isSuccess = "Success"))
                }
                is Resource.Loading -> {
                    _signUpState.send(LoginState(isLoading = true))
                }
                is Resource.Error -> {
                    _signUpState.send(LoginState(isError = result.message))
                }
            }
        }
    }
}