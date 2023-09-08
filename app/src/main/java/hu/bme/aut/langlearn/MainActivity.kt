package hu.bme.aut.langlearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.langlearn.navigation.AuthNavigation
import hu.bme.aut.langlearn.presentation.MainMenu
import hu.bme.aut.langlearn.ui.theme.LangLearnTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LangLearnTheme {
                val navController = rememberNavController()
                MainMenu(outerNavController = navController)
            }
        }
    }
}