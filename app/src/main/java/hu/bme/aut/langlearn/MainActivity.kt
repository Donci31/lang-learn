package hu.bme.aut.langlearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.langlearn.navigation.AuthNavigation
import hu.bme.aut.langlearn.ui.theme.LangLearnTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LangLearnTheme {
                AuthNavigation()
            }
        }
    }
}