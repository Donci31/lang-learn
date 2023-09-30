package hu.bme.aut.langlearn.presentation.profile_screen.main_profile_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.langlearn.data.AuthRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    var username by mutableStateOf(repository.getCurrentUser()?.displayName)

    var languages = mutableStateListOf<String>()

    var achievements = mutableStateListOf<String>()

    fun logout() {
        repository.logout()
    }
}