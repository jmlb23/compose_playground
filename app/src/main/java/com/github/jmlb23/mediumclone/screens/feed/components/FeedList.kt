package com.github.jmlb23.mediumclone.screens.feed.components

import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.runtime.Composable
import com.github.jmlb23.mediumclone.data.models.Article

@Composable
fun FeedList(feeds: List<Article>,add: () -> Unit) {
    LazyColumnForIndexed(
        items = feeds
    ) { i, item ->
        if(feeds.size - 5 == i) add()
        FeedItem(art = item)
    }
}