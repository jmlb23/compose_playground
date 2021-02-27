package com.github.jmlb23.mediumclone.screens.feed.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.github.jmlb23.mediumclone.data.models.Article

@Composable
fun FeedList(feeds: List<Article>, controller: NavHostController, add: @Composable () -> Unit) {
    LazyColumn {
        items(feeds.size) {
            Box(modifier = Modifier.clickable(onClick = { controller.navigate("/feed/${feeds[it].slug}") })) {
                FeedItem(art = feeds[it], controller)
            }
        }
        item {
            add()
        }
    }
}