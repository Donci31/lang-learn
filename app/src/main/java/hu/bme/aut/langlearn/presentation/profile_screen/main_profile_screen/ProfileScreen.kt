package hu.bme.aut.langlearn.presentation.profile_screen.main_profile_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.langlearn.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    logoutOnClick: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val languagesState by viewModel.languages.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "User Profile")
                }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .fillMaxWidth()
                    .clip(CircleShape)
                    .padding(8.dp)
            )

            Text(
                text = "Username: ${viewModel.username}",
                fontWeight = FontWeight.Bold
            )

            Text(text = "Learning Languages:")

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                languagesState.forEach { language ->
                    Chip(text = language, modifier = Modifier.height(32.dp))
                }
            }

            Text(text = "Achievements:", fontWeight = FontWeight.Bold)

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                viewModel.achievements.forEach { achievement ->
                    Chip(text = achievement, modifier = Modifier.height(32.dp))
                }
            }

            OutlinedButton(
                onClick = {
                    viewModel.logout()
                    logoutOnClick()
                },
                modifier = Modifier.fillMaxWidth(fraction = .75f)
            ) {
                Text(text = "Logout")
            }
        }
    }
}
