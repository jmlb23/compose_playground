package com.github.jmlb23.mediumclone.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.navigate
import com.github.jmlb23.mediumclone.AmbientCoroutineScope
import com.github.jmlb23.mediumclone.AmbientNavHostController
import com.github.jmlb23.mediumclone.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Splash() {
    val navHostController = AmbientNavHostController.current
    val coroutineScope = AmbientCoroutineScope.current

    Box(modifier = Modifier.background(MaterialTheme.colors.primary).fillMaxWidth(1f).fillMaxHeight(1f)) {
        Image(imageVector = vectorResource(
                R.drawable.ic_launcher_foreground
            ),
            Modifier.height(100.dp).width(100.dp).align(Alignment.Center)
        )
        Text(text = "Version 1.0.0",modifier = Modifier.align(Alignment.BottomCenter),color = MaterialTheme.colors.primaryVariant)
    }

    onCommit(callback = {
        val job = coroutineScope.launch {
            delay(2000)
            navHostController.navigate("/home")
        }
        onDispose {
            job.cancel()
        }
    })
}

