package hu.bme.aut.langlearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import hu.bme.aut.langlearn.ui.MainMenu
import hu.bme.aut.langlearn.ui.theme.LangLearnTheme

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