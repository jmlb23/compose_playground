package com.github.jmlb23.mediumclone.state

import com.github.jmlb23.mediumclone.data.models.Article
import com.github.jmlb23.mediumclone.data.models.Comment
import com.github.jmlb23.mediumclone.data.models.User


sealed class AppActions(open val name: String) {
    sealed class FeedActions(override val name: String) : AppActions("FeedActions") {
        object ChangePageAction : FeedActions("ChangePageAction")
        object GetPages : FeedActions("ChangePageAction")
        data class SetPagesAction(val value: List<Article>) : FeedActions("SetPagesAction")
    }

    sealed class DetailActions : AppActions("DetailActions") {
        data class GetDetail(val slug: String) : DetailActions()
        data class SetArticle(val article: Article) : DetailActions()
        data class SetComments(val value: List<Comment>) : DetailActions()
    }

    sealed class LoginActions : AppActions("LoginActions"){
        data class SendLoginAction(val username: String, val password: String): LoginActions()
        data class SetCurrentUser(val user: User): LoginActions()
    }

    sealed class FavoritesActions : AppActions("FavoritesActions"){
        object GetFavorites : FavoritesActions()
        data class SetFavorites(val favs: List<Article>) : FavoritesActions()
        data class AddFav(val slug: String) : FavoritesActions()
        data class RemoveFav(val slug: String) : FavoritesActions()
    }
}