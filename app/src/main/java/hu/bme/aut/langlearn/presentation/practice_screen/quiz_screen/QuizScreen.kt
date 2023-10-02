package hu.bme.aut.langlearn.presentation.practice_screen.quiz_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import hu.bme.aut.langlearn.presentation.practice_screen.PracticeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: PracticeViewModel = viewModel(),
) {
    var userInput by rememberSaveable { mutableStateOf("") }

    val deckState by viewModel.cardList.collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Quiz Screen")
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
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(64.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = deck.words[viewModel.currentCardIndex].foreignWord,
                            textAlign = TextAlign.Center,
                            style = typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            value = userInput,
                            onValueChange = { userInput = it },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if (viewModel.currentCardIndex == deck.words.size - 1) {
                                        navController.popBackStack()
                                    } else {
                                        if (viewModel.currentCardIndex > 0) {
                                            viewModel.currentCardIndex--
                                        }
                                    }
                                }
                            ),
                            label = { Text("Your translation") }
                        )
                    }
                }
            }
        }
    }
}