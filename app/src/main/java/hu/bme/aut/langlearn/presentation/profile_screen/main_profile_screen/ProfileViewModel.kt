package hu.bme.aut.langlearn.presentation.profile_screen.main_profile_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.domain.use_cases.profile_screen.GetPracticedLanguagesUseCase
import hu.bme.aut.langlearn.domain.use_cases.profile_screen.GetProfilePictureUseCase
import hu.bme.aut.langlearn.domain.use_cases.profile_screen.GetUsernameUseCase
import hu.bme.aut.langlearn.domain.use_cases.profile_screen.LogoutUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    getUsernameUseCase: GetUsernameUseCase,
    getProfilePictureUseCase: GetProfilePictureUseCase,
    getPracticedLanguagesUseCase: GetPracticedLanguagesUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val username = getUsernameUseCase()

    val profilePicture = getProfilePictureUseCase()

    var languages = getPracticedLanguagesUseCase()

    fun logout() {
        logoutUseCase()
    }
}