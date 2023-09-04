package hu.bme.aut.langlearn.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector


enum class NavItem(
    val route: String,
    val icon: ImageVector,
) {
    Quiz(
        route = "quiz",
        icon = Icons.Filled.Home
    ),
    Practice(
        route = "practice",
        icon = Icons.Filled.ThumbUp
    ),
    Profile(
        route = "profile",
        icon = Icons.Filled.AccountCircle
    )
}