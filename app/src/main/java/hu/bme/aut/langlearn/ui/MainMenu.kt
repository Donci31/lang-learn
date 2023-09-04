package hu.bme.aut.langlearn.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.langlearn.ui.navigation.BottomNavigationBar
import hu.bme.aut.langlearn.ui.navigation.NavItem
import hu.bme.aut.langlearn.ui.practice.Practice
import hu.bme.aut.langlearn.ui.profile.Profile
import hu.bme.aut.langlearn.ui.quiz.Quiz

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu() {
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
            composable(NavItem.Quiz.route) { Quiz(navController) }
            composable(NavItem.Practice.route) { Practice(navController) }
            composable(NavItem.Profile.route) { Profile(navController) }
        }
    }
}

@Preview
@Composable
fun MainMenuPreview() {
    MainMenu()
}
