package hu.bme.aut.langlearn.presentation.singup_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.AuthRepository
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState = _signUpState.asStateFlow()

    fun signUpUser(email: String, password: String) {
        repository.registerUser(email, password)
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _signUpState.value = SignUpState(isSuccess = "Success")
                    }

                    is Resource.Loading -> {
                        _signUpState.value = SignUpState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _signUpState.value = SignUpState(isError = result.message)
                    }
                }
            }.launchIn(viewModelScope)
    }
}