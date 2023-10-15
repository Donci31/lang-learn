package hu.bme.aut.langlearn.presentation.practice_screen.sentence_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeFrame

@Composable
fun SentenceScreen(
    navController: NavController,
    viewModel: SentenceViewModel = hiltViewModel(),
) {
    PracticeFrame(viewModel = viewModel) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = viewModel.curSentence)
        }
    }
}