package hu.bme.aut.langlearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.langlearn.presentation.MainMenu
import hu.bme.aut.langlearn.presentation.quiz_screen.HomeScreen
import hu.bme.aut.langlearn.presentation.singup_screen.SignUpScreen
import hu.bme.aut.langlearn.ui.theme.LangLearnTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LangLearnTheme {
                MainMenu()
            }
        }
    }
}