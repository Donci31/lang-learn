package hu.bme.aut.langlearn.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector


enum class BottomNavItem(
    val route: String,
    val icon: ImageVector,
) {
    Quiz(
        route = "quiz",
        icon = Icons.Default.Home
    ),
    Practice(
        route = "practice",
        icon = Icons.Default.ThumbUp
    ),
    Profile(
        route = "profile",
        icon = Icons.Default.Face
    )
}