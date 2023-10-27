package hu.bme.aut.langlearn.presentation.practice_screen.sentence_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeFrame

@Composable
fun SentenceScreen(
    navController: NavController,
    viewModel: SentenceViewModel = hiltViewModel(),
) {
    PracticeFrame(viewModel = viewModel) {
        viewModel.curSentence?.let { curSentence ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = curSentence,
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )

                Column {
                    viewModel.quizAnswers.forEach { choice ->
                        Button(
                            onClick = {
                                viewModel.checkCorrectAnswer(choice)

                                if (viewModel.isLastWord()) {
                                    viewModel.saveProgress()
                                    navController.popBackStack()
                                } else {
                                    viewModel.resetQuiz()
                                    viewModel.goToNextWord()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Text(text = choice.foreignWord)
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