package com.github.jmlb23.mediumclone.data.models

import com.github.jmlb23.mediumclone.data.models.Article


data class MultipleArticlesResponse(
    var articles: List<Article>,
    var articlesCount: Int
)
