package hu.bme.aut.langlearn.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ThumbUp
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
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    )

    data object Practice : NavItem(
        route = "practice",
        nestedRoute = "main_practice_screen",
        label = "Practice",
        selectedIcon = Icons.Filled.ThumbUp,
        unSelectedIcon = Icons.Outlined.ThumbUp
    )

    data object Profile : NavItem(
        route = "profile",
        nestedRoute = "main_profile_screen",
        label = "Profile",
        selectedIcon = Icons.Filled.AccountCircle,
        unSelectedIcon = Icons.Outlined.AccountCircle
    )
}
