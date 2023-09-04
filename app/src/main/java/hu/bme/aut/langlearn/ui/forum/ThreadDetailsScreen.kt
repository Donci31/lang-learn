package hu.bme.aut.langlearn.ui.forum

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ThreadDetailsScreen(threadTitle: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Thread Details",
            style = typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Thread Title:",
            style = typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
        Text(text = threadTitle ?: "", style = typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Thread Content:",
            style = typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "This is a sample thread content. You can replace it with the actual content of the thread.",
            style = typography.bodyLarge
        )
    }
}