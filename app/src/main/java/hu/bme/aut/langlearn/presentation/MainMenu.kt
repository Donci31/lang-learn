package hu.bme.aut.langlearn.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.langlearn.navigation.BottomNavigationBar
import hu.bme.aut.langlearn.navigation.NavItem
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeScreen
import hu.bme.aut.langlearn.presentation.profile_screen.ProfileScreen
import hu.bme.aut.langlearn.presentation.quiz_screen.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(
    outerNavController: NavController
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = NavItem.values()
            ) { navItem ->
                navController.navigate(navItem.route) {
                    popUpTo(navController.graph.id)
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = NavItem.Quiz.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(NavItem.Quiz.route) { HomeScreen(navController) }
            composable(NavItem.Practice.route) { PracticeScreen(navController) }
            composable(NavItem.Profile.route) {
                ProfileScreen(
                    username = "User",
                    learningLanguages = listOf("German", "French"),
                    achievements = listOf("Beginner", "Intermediate"),
                )
            }
        }
    }
}

@Preview
@Composable
fun MainMenuPreview() {
    val navController = rememberNavController()
    MainMenu(navController)
}
