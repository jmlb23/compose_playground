package com.github.jmlb23.mediumclone.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.jmlb23.mediumclone.Ambients
import com.github.jmlb23.mediumclone.state.AppActions
import kotlinx.coroutines.launch

@Composable
fun Login() {
    val usernameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val store = Ambients.LocalStore.current
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Text(text = "Login", style = MaterialTheme.typography.h3)
        }
        item {
            OutlinedTextField(
                value = usernameState.value,
                onValueChange = { usernameState.value = it },
                placeholder = { Text(text = "username") },
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            OutlinedTextField(
                value = passwordState.value,
                onValueChange = { passwordState.value = it },
                placeholder = { Text(text = "password") },
                visualTransformation = PasswordVisualTransformation()
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Button(onClick = {
                scope.launch {
                    store.dispatch(
                        AppActions.LoginActions.SendLoginAction(
                            usernameState.value,
                            passwordState.value
                        )
                    )
                }
            }) {
                Text("Login")
            }
        }
    }

}

@Preview
@Composable
fun Login_Preview() {
    Login()
}