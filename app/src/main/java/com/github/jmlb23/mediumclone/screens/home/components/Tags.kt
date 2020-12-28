package com.github.jmlb23.mediumclone.screens.home.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable

@Composable
fun Tags(xs: List<String>) {
    LazyColumnFor(items = xs) { x ->
        Text(text = x)
    }
}
