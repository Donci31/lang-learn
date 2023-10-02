package hu.bme.aut.langlearn.presentation.practice_screen.main_practice_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PracticeScreen(
    navController: NavController,
    viewModel: PracticeScreenViewModel = hiltViewModel(),
) {
    val practiceItems = listOf(
        PracticeItem(
            name = "FlipCard",
            description = "Quiz involved with flipping cards",
            destination = "flip_card_screen"
        ),
        PracticeItem(
            name = "Quiz",
            description = "Multiple choice quiz",
            destination = "multiple_choice_quiz_screen"
        ),
        PracticeItem(
            name = "Sentence",
            description = "Complete the sentence",
            destination = "sentence_screen"
        )
    )

    val deckNameListState by viewModel.deckNameList.collectAsState(initial = null)

    val pageCount = practiceItems.size
    val pagerState = rememberPagerState(pageCount = { pageCount })

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Practice")
                }
            )
        },
    ) { padding ->
        deckNameListState?.let { deckIdsAndNames ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                HorizontalPager(
                    modifier = Modifier.weight(1f),
                    state = pagerState
                ) { page ->
                    PracticePage(
                        navController = navController,
                        deckNameList = deckIdsAndNames,
                        practiceItem = practiceItems[page]
                    )
                }
                Row(
                    Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(10.dp)
                        )
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