package hu.bme.aut.langlearn.presentation.practice_screen.flip_card_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlipCardScreen(
    navController: NavController,
    viewModel: PracticeViewModel = hiltViewModel(),
) {
    val deckState by viewModel.cardList.collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "FlipCard Screen")
                }
            )
        },
    ) { padding ->
        deckState?.let { deck ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                val progress by animateFloatAsState(
                    targetValue = (viewModel.currentCardIndex + 1).toFloat() / deck.words.size,
                    label = "progress animation"
                )
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    FlipCard(
                        modifier = Modifier
                            .fillMaxWidth(.8f)
                            .aspectRatio(1.5f),
                        foreignWord = deck.words[viewModel.currentCardIndex].foreignWord,
                        englishTranslation = deck.words[viewModel.currentCardIndex].englishTranslation
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (viewModel.currentCardIndex > 0) {
                                viewModel.currentCardIndex--
                            }
                        },
                        enabled = viewModel.currentCardIndex > 0
                    ) {
                        Text(text = "Previous")
                    }

                    if (viewModel.currentCardIndex == deck.words.size - 1) {
                        Button(
                            onClick = navController::popBackStack
                        ) {
                            Text(text = "Finish")
                        }
                    } else {
                        Button(
                            onClick = {
                                if (viewModel.currentCardIndex < deck.words.size - 1) {
                                    viewModel.currentCardIndex++
                                }
                            }
                        ) {
                            Text(text = "Next")
                        }
                    }
                }
            }
        } ?: Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}