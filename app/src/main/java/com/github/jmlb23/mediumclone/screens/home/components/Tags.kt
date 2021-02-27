package com.github.jmlb23.mediumclone.screens.home.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Tags(xs: List<String>) {
    LazyColumn{
        items(xs){
            Text(text = it)
        }
    }
}
