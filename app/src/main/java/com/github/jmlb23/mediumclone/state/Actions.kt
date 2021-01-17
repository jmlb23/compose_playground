package com.github.jmlb23.mediumclone.state

import com.github.jmlb23.mediumclone.data.models.Article
import com.github.jmlb23.mediumclone.data.models.Comment


sealed class AppActions{
    sealed class FeedActions : AppActions(){
        object ChangePageAction : FeedActions()
        data class SetPagesAction(val value: List<Article>) : FeedActions()
    }

    sealed class DetailActions : AppActions(){
        data class GetDetail(val slug: String) : DetailActions()
        data class SetArticle(val article: Article) : DetailActions()
        data class SetComments(val value: List<Comment>) : DetailActions()
    }
}