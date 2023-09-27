package hu.bme.aut.langlearn.presentation.practice_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Practice Screen")
                }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PracticeItem(
                navController = navController,
                name = "FlipCard",
                description = "Quiz involved with flipping cards",
                destination = "flip_card"
            )
            PracticeItem(
                navController = navController,
                name = "Listening",
                description = "Improve language by listening to words",
                destination = "flip_card"
            )
            PracticeItem(
                navController = navController,
                name = "Speaking",
                description = "Improve language by speaking",
                destination = "flip_card"
            )
            Divider()
            PracticeItem(
                navController = navController,
                name = "Quiz",
                description = "Quiz involved with flipping cards",
                destination = "flip_card"
            )
            PracticeItem(
                navController = navController,
                name = "Multiple choice quiz",
                description = "Improve language by listening to words",
                destination = "flip_card"
            )
            PracticeItem(
                navController = navController,
                name = "Sentence",
                description = "Complete the sentence",
                destination = "flip_card"
            )
        }
    }
}