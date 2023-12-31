package hu.bme.aut.langlearn.presentation.login_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.loginState.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(
                    text = "Login",
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.displayLarge
                )

                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = viewModel::updateEmail,
                    label = { Text("Email") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = "Email icon"
                        )
                    }
                )

                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = viewModel::updatePassword,
                    label = { Text("Password") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = "Password icon"
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Button(
                    onClick = viewModel::loginUser
                ) {
                    Text(text = "Login")
                }

                TextButton(
                    onClick = {
                        navController.navigate(route = "register")
                    }
                ) {
                    Text(text = "Don't have an account? Sign up now!")
                }

                viewModel.loginState.isSuccess?.let {
                    navController.navigate(route = "main_menu") {
                        popUpTo(navController.graph.id)
                    }
                }

                viewModel.loginState.isError?.let {
                    Toast.makeText(LocalContext.current, it, Toast.LENGTH_SHORT).show()
                    viewModel.resetState()
                }
            }
        }
    }
}
