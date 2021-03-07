package com.github.jmlb23.mediumclone.data.models

import kotlinx.serialization.Serializable

@Serializable
data class MultipleArticlesResponse(
    var articles: List<Article>,
    var articlesCount: Int
)
