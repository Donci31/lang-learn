package hu.bme.aut.langlearn.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hu.bme.aut.langlearn.ui.forum.ForumScreen
import hu.bme.aut.langlearn.ui.forum.ThreadDetailsScreen
import hu.bme.aut.langlearn.ui.navigation.BottomNavigationBar
import hu.bme.aut.langlearn.ui.navigation.NavItem
import hu.bme.aut.langlearn.ui.practice.PracticeScreen
import hu.bme.aut.langlearn.ui.profile.ProfileScreen
import hu.bme.aut.langlearn.ui.quiz.HomeScreen

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
            composable(NavItem.Quiz.route) { HomeScreen(navController) }
            composable(NavItem.Practice.route) { PracticeScreen(navController) }
            composable(NavItem.Forum.route) { ForumScreen(navController) }
            composable(NavItem.Profile.route) {
                ProfileScreen(
                    username = "Donci",
                    learningLanguages = listOf("German", "French"),
                    achievements = listOf("Beginner", "Intermediate"),
                    lessonsCompleted = 25,
                    vocabularyLearned = 150
                )
            }
            composable(
                "threadDetails/{threadTitle}",
                arguments = listOf(navArgument("threadTitle") { type = NavType.StringType })
            ) { backStackEntry ->
                val threadTitle = backStackEntry.arguments?.getString("threadTitle")
                ThreadDetailsScreen(threadTitle)
            }
        }
    }
}

@Preview
@Composable
fun MainMenuPreview() {
    MainMenu()
}
