package hu.bme.aut.langlearn.presentation.flip_card_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlipCardScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "FlipCard Screen")
                }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            val progress by animateFloatAsState(
                targetValue = 0.2f,
                label = "progress animation"
            )
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                FlipCard(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .aspectRatio(1.5f),
                    foreignWord = "Bueno",
                    englishTranslation = "Good"
                )
            }
        }
    }
}