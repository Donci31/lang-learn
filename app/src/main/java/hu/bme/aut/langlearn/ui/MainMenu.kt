package hu.bme.aut.langlearn.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.langlearn.ui.navigation.BottomNavigationBar
import hu.bme.aut.langlearn.ui.navigation.BottomNavItem
import hu.bme.aut.langlearn.ui.practice.Practice
import hu.bme.aut.langlearn.ui.profile.Profile
import hu.bme.aut.langlearn.ui.quiz.Quiz

@OptIn(ExperimentalMaterial3Api::class, ExperimentalStdlibApi::class)
@Composable
fun MainMenu() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = BottomNavItem.values()
            ) { item ->
                navController.navigate(item.route)
            }
        }
    ) { innerPadding ->
        Navigation(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}



@Composable
fun Navigation(modifier: Modifier, navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Quiz.route, modifier) {
        composable(BottomNavItem.Quiz.route) { Quiz(navController) }
        composable(BottomNavItem.Practice.route) { Practice(navController) }
        composable(BottomNavItem.Profile.route) { Profile(navController) }
    }
}

@Preview
@Composable
fun MainMenuPreview() {
    MainMenu()
}
