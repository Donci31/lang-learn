package hu.bme.aut.langlearn.presentation.profile_screen.main_profile_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    auth: FirebaseAuth
): ViewModel() {

    var username by mutableStateOf(auth.currentUser?.displayName ?: "Joe")

    var languages = mutableStateListOf<String>()

    var achievements = mutableStateListOf<String>()
}