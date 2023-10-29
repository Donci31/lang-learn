package hu.bme.aut.langlearn.presentation.deck_screen.main_deck_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlin.math.floor

@Composable
fun ScoreBar(
    rating: Double,
) {
    val filledStars = floor(rating * 5).toInt()
    Row {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Filled star",
                tint = Color.Yellow
            )
        }
        repeat(5 - filledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = "Unfilled star"
            )
        }
    }
}