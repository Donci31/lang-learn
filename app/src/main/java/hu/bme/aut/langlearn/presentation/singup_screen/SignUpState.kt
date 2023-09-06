package hu.bme.aut.langlearn.presentation.singup_screen

data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: String? = null,
    val isError: String? = null
)