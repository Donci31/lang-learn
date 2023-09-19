package hu.bme.aut.langlearn.presentation.login_screen

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.AuthRepository
import hu.bme.aut.langlearn.presentation.singup_screen.SignUpState
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun loginUser() {
        repository.loginUser(email, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _loginState.value = LoginState(isSuccess = "Success")
                }

                is Resource.Loading -> {
                    _loginState.value = LoginState(isLoading = true)
                }

                is Resource.Error -> {
                    _loginState.value = LoginState(isError = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun resetState() {
        _loginState.value = LoginState()
    }
}