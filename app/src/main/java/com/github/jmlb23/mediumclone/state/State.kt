package com.github.jmlb23.mediumclone.state

import com.github.jmlb23.mediumclone.data.models.Article
import com.github.jmlb23.mediumclone.data.models.Comment


data class FeedState(val page: Int? = null, val article: List<Article>? = emptyList())
data class DetailState(val article: Article? = null, val comments: List<Comment> = emptyList())
data class AppState(val feed: FeedState = FeedState(), val detail: DetailState = DetailState())