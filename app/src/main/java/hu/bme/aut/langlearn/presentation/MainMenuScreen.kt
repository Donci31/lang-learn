package hu.bme.aut.langlearn.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.langlearn.navigation.BottomNavigationBar
import hu.bme.aut.langlearn.navigation.NavItem
import hu.bme.aut.langlearn.presentation.deck_screen.add_deck_screen.AddDeckScreen
import hu.bme.aut.langlearn.presentation.deck_screen.main_deck_screen.DecksScreen
import hu.bme.aut.langlearn.presentation.practice_screen.flip_card_screen.FlipCardScreen
import hu.bme.aut.langlearn.presentation.practice_screen.main_practice_screen.PracticeScreen
import hu.bme.aut.langlearn.presentation.practice_screen.quiz_screen.QuizScreen
import hu.bme.aut.langlearn.presentation.practice_screen.sentence_screen.SentenceScreen
import hu.bme.aut.langlearn.presentation.profile_screen.main_profile_screen.ProfileScreen
import hu.bme.aut.langlearn.presentation.profile_screen.settings_screen.SettingsScreen

@Composable
fun MainMenuScreen(
    logoutOnClick: () -> Unit,
) {
    val navController = rememberNavController()
    val bottomNavItems = listOf(NavItem.Quiz, NavItem.Practice, NavItem.Profile)

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = bottomNavItems
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
            navigation(
                startDestination = NavItem.Quiz.nestedRoute,
                route = NavItem.Quiz.route
            ) {
                composable(NavItem.Quiz.nestedRoute) {
                    DecksScreen(navController)
                }
                composable("add_new_deck_screen") {
                    AddDeckScreen(navController)
                }
            }
            navigation(
                startDestination = NavItem.Practice.nestedRoute,
                route = NavItem.Practice.route
            ) {
                composable(NavItem.Practice.nestedRoute) {
                    PracticeScreen(navController)
                }
                composable("flip_card_screen/{deckId}") {
                    FlipCardScreen(navController)
                }
                composable("quiz_screen/{deckId}") {
                    QuizScreen(navController)
                }
                composable("sentence_screen/{deckId}") {
                    SentenceScreen(navController)
                }
            }
            navigation(
                startDestination = NavItem.Profile.nestedRoute,
                route = NavItem.Profile.route
            ) {
                composable(NavItem.Profile.nestedRoute) {
                    ProfileScreen(
                        navController = navController,
                        logoutOnClick = logoutOnClick
                    )
                }
                composable("settings_screen") {
                    SettingsScreen()
                }
            }
        }
    }
}