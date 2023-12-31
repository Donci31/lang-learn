package hu.bme.aut.langlearn.presentation.singup_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.signUpState.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(
                    text = "Sign Up",
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.displayLarge
                )

                OutlinedTextField(
                    value = viewModel.userName,
                    onValueChange = viewModel::updateUserName,
                    label = { Text("Username") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Username icon"
                        )
                    }
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

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = viewModel.gender == Gender.MALE,
                        onClick = { viewModel.gender = Gender.MALE }
                    )
                    Text(text = "Male")

                    RadioButton(
                        selected = viewModel.gender == Gender.FEMALE,
                        onClick = { viewModel.gender = Gender.FEMALE }
                    )
                    Text(text = "Female")
                }

                Button(
                    onClick = viewModel::signUpUser
                ) {
                    Text(text = "Sign Up")
                }

                TextButton(
                    onClick = {
                        navController.navigate("login")
                    }
                ) {
                    Text(text = "Already have an account? Login here!")
                }

                (viewModel.signUpState.isSuccess ?: viewModel.signUpState.isError)?.let {
                    Toast.makeText(LocalContext.current, it, Toast.LENGTH_SHORT).show()
                    viewModel.resetState()
                }
            }
        }
    }
}
