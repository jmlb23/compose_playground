package com.github.jmlb23.mediumclone.state

import com.github.jmlb23.mediumclone.data.models.Article


data class FeedState(val page: Int? = null, val article: List<Article>? = emptyList())
data class AppState(val feed: FeedState? = FeedState())