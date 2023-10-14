package hu.bme.aut.langlearn.presentation.practice_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
fun PracticeFrame(
    viewModel: PracticeViewModel,
    content: @Composable (ColumnScope.() -> Unit)
) {
    viewModel.deck?.let { deck ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = deck.name)
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
                content()
            }
        }
    } ?: Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}