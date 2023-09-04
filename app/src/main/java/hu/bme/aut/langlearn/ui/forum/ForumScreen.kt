package hu.bme.aut.langlearn.ui.forum

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Language Exchange Forum") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "New Thread")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            ThreadList(navController = navController, threads = dummyThreads)
        }
    }
}

@Composable
fun ThreadList(navController: NavController, threads: List<Thread>) {
    LazyColumn {
        items(threads) { thread ->
            ThreadItem(navController = navController, thread = thread)
            Divider()
        }
    }
}

@Composable
fun ThreadItem(navController: NavController, thread: Thread) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { navController.navigate("threadDetails/${thread.title}") },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "User Avatar")
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = thread.title, style = typography.displaySmall)
            Text(text = "By ${thread.author} • ${thread.date}", style = typography.bodyMedium)
        }
    }
}

data class Thread(val title: String, val author: String, val date: String)

val dummyThreads = listOf(
    Thread("Language Exchange Partner Needed", "User123", "2 hours ago"),
    Thread("Practicing Conversations in French", "Learner45", "1 day ago"),
    Thread("Looking for Spanish Speaker", "Traveler789", "2 days ago")
)
