package com.github.jmlb23.mediumclone.state

import com.github.jmlb23.mediumclone.data.models.Article


sealed class AppActions{
    sealed class FeedActions : AppActions(){
        object ChangePageAction : FeedActions()
        data class SetPagesAction(val value: List<Article>) : FeedActions()
    }
}