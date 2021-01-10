package com.github.jmlb23.mediumclone.screens.feed.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import com.github.jmlb23.mediumclone.data.models.Article

@Composable
fun FeedList(feeds: List<Article>, add: () -> Unit) {
    LazyColumn {
        items(feeds) {
            FeedItem(art = it)
        }
        item {
            onCommit{
                add()
            }
        }
    }
}