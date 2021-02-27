package com.github.jmlb23.mediumclone.screens.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

@Composable
fun Login(controller: NavHostController) {
    val usernameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {

            TextField(
                value = usernameState.value,
                onValueChange = { usernameState.value = it },
                placeholder = { Text(text = "username") },
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            TextField(
                value = passwordState.value,
                onValueChange = { passwordState.value = it },
                placeholder = { Text(text = "password") })
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Button(onClick = {
               controller.navigate("/profile")
            }) {
                Text("Login")
            }
        }
    }

}

@Preview
@Composable
fun Login_Preview() {
    Login(rememberNavController())
}