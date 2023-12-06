package hu.bme.aut.langlearn.presentation.practice_screen.flip_card_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeFrame

@Composable
fun FlipCardScreen(
    navController: NavController,
    viewModel: FlipCardViewModel = hiltViewModel(),
) {
    PracticeFrame(viewModel = viewModel) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
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
                onClick = viewModel::goToPreviousWord,
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
                    onClick = viewModel::goToNextWord
                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}