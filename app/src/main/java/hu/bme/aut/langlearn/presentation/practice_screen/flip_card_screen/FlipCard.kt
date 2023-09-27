package hu.bme.aut.langlearn.presentation.practice_screen.flip_card_screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlipCard(
    modifier: Modifier = Modifier,
    foreignWord: String,
    englishTranslation: String
) {
    var angle by rememberSaveable {
        mutableFloatStateOf(0f)
    }

    val rotation = animateFloatAsState(
        targetValue = angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        ),
        label = "Card animation"
    )

    Card(
        onClick = { angle = 180f - angle },
        modifier = Modifier
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 12f * density
            }
    ) {
        if (rotation.value <= 90f) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Text(text = foreignWord)
            }
        } else {
            Box(
                modifier = modifier
                    .graphicsLayer {
                        rotationY = 180f
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = englishTranslation)
            }
        }
    }
}