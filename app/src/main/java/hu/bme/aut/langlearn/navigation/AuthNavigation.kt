package hu.bme.aut.langlearn.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.langlearn.presentation.MainMenu
import hu.bme.aut.langlearn.presentation.login_screen.LoginScreen
import hu.bme.aut.langlearn.presentation.singup_screen.SignUpScreen

@Composable
fun AuthNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login",
    ) {
        composable("login") { LoginScreen(navController) }
        composable("register") { SignUpScreen(navController) }
        composable("main_menu") { MainMenu(navController) }
    }
}