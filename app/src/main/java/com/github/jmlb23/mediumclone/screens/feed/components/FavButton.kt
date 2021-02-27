package com.github.jmlb23.mediumclone.screens.feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun FavButton(onClick: () -> Unit) {
    IconButton(
        content = {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorite"
            )
        },
        modifier = Modifier
            .background(Color.White, shape = RectangleShape)
            .size(24.dp),
        onClick = onClick
    )
}
