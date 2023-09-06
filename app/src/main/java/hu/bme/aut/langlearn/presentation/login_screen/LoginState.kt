package hu.bme.aut.langlearn.presentation.login_screen

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: String? = null,
    val isError: String? = null
)