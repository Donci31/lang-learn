package hu.bme.aut.langlearn.presentation.flip_card_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlipCardScreen(
    navController: NavController,
    viewModel: FlipCardViewModel = viewModel(),
) {
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
            modifier = Modifier.padding(padding)
        ) {
            val progress by animateFloatAsState(
                targetValue = viewModel.getProgress(),
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
                    foreignWord = viewModel.getForeignWord(),
                    englishTranslation = viewModel.getEnglishTranslation()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = viewModel::goToPreviousCard,
                    enabled = viewModel.isNotFirstWord()
                ) {
                    Text(text = "Previous")
                }

                if (viewModel.isLastWord()) {
                    Button(
                        onClick = navController::popBackStack
                    ) {
                        Text(text = "Finish")
                    }
                } else {
                    Button(
                        onClick = viewModel::goToNextCard
                    ) {
                        Text(text = "Next")
                    }
                }
            }
        }
    }
}