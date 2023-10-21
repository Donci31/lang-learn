package hu.bme.aut.langlearn.presentation.deck_screen.add_deck_screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordItem(
    modifier: Modifier,
    sequenceNumber: Int,
    word: StatefulWord,
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
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Word $sequenceNumber"
                )
                OutlinedTextField(
                    value = word.foreignWord.value,
                    onValueChange = { word.foreignWord.value = it },
                    label = { Text("foreignWord") },
                )
            }
        } else {
            Column(
                modifier = modifier
                    .graphicsLayer {
                        rotationY = 180f
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Word $sequenceNumber"
                )
                OutlinedTextField(
                    value = word.englishTranslation.value,
                    onValueChange = { word.englishTranslation.value = it },
                    label = { Text("englishTranslation") },
                )
            }
        }
    }
}