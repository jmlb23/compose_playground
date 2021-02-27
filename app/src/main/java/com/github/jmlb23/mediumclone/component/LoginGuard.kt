package com.github.jmlb23.mediumclone.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.github.jmlb23.mediumclone.Ambients

@Composable
fun LoginGuard(nav: NavHostController, component: @Composable () -> Unit) {
    val store = Ambients.LocalStore.current
    val token = store.select { it.token }.collectAsState(initial = null)
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        token.value?.let {
            component()
        } ?: run {
            TextButton(onClick = {
                nav.navigate("/profile")
            }) {
                Text(text = "Login to view content")
            }
        }
    }

}