package hu.bme.aut.langlearn.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector


sealed class NavItem(
    val route: String,
    val nestedRoute: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) {
    data object Quiz : NavItem(
        route = "deck",
        nestedRoute = "main_deck_screen",
        label = "Deck",
        selectedIcon = Icons.Filled.Add,
        unSelectedIcon = Icons.Outlined.Add
    )

    data object Practice : NavItem(
        route = "practice",
        nestedRoute = "main_practice_screen",
        label = "Practice",
        selectedIcon = Icons.Filled.PlayArrow,
        unSelectedIcon = Icons.Outlined.PlayArrow
    )

    data object Profile : NavItem(
        route = "profile",
        nestedRoute = "main_profile_screen",
        label = "Profile",
        selectedIcon = Icons.Filled.AccountCircle,
        unSelectedIcon = Icons.Outlined.AccountCircle
    )
}
