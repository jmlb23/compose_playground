package com.github.jmlb23.mediumclone.state

import android.util.Log
import com.github.jmlb23.mediumclone.data.models.LoginUser
import com.github.jmlb23.mediumclone.data.models.LoginUserRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

val middlewarePagination: Middleware<AppState, AppActions, AppEnviroment> =
    {
        { store ->
            { dispatcher ->
                { action ->
                    withContext(Dispatchers.IO) {
                        Log.d(
                            "middlewarePagination",
                            "action: ${action::class.simpleName} page: ${store.state().feed.page}"
                        )
                        when (action) {
                            AppActions.FeedActions.GetPages -> {
                                val page = store.state().feed.page
                                val pages = store.enviroment.factories.getArticlesService()
                                    .getArticles(limit = 10, offset = page * 10)
                                it(AppActions.FeedActions.SetPagesAction(pages.articles))
                            }
                            else -> dispatcher(action)
                        }
                    }
                }
            }
        }
    }
val middlewareDetailArticle: Middleware<AppState, AppActions, AppEnviroment> =
    {
        { store ->
            { dispatcher ->
                { action ->
                    withContext(Dispatchers.IO) {
                        Log.d(
                            "middlewareDetailArticle",
                            "action: ${action::class.simpleName} page: ${store.state().feed.page}"
                        )
                        when (action) {
                            is AppActions.DetailActions.GetDetail -> {
                                val pages = store.enviroment.factories.getArticlesService()
                                    .getArticle(action.slug)
                                it(AppActions.DetailActions.SetArticle(pages.article))
                            }
                            else -> dispatcher(action)
                        }
                    }
                }
            }
        }
    }

val middlewareDetailComment: Middleware<AppState, AppActions, AppEnviroment> =
    {
        { store ->
            { dispatcher ->
                { action ->
                    withContext(Dispatchers.IO) {
                        Log.d(
                            "middlewareDetailComment",
                            "action: ${action::class.simpleName} page: ${store.state().feed.page}"
                        )
                        when (action) {
                            is AppActions.DetailActions.GetDetail -> {

                                val pages = store.enviroment.factories.getCommentsService()
                                    .getArticleComments(action.slug)
                                it(AppActions.DetailActions.SetComments(pages.comments))
                            }

                            else -> dispatcher(action)
                        }
                    }
                }
            }
        }
    }


val middlewareLogin: Middleware<AppState, AppActions, AppEnviroment> =
    {
        { store ->
            { dispatcher ->
                { action ->
                    withContext(Dispatchers.IO) {
                        when (action) {
                            is AppActions.LoginActions.SendLoginAction -> {
                                val user = store.enviroment.factories.getUserAndAuthService()
                                    .login(
                                        LoginUserRequest(
                                            LoginUser(
                                                action.username,
                                                action.password
                                            )
                                        )
                                    )
                                it(AppActions.LoginActions.SetCurrentUser(user.user))
                            }
                            else -> dispatcher(action)
                        }
                    }
                }
            }
        }
    }

val middlewareCallFavs: Middleware<AppState, AppActions, AppEnviroment> =
    {
        { store ->
            { dispatcher ->
                { action ->
                    val user = store.state().user

                    withContext(Dispatchers.IO) {
                        when (action) {
                            is AppActions.FavoritesActions.GetFavorites -> {
                                val articles = store.enviroment.factories.getArticlesService()
                                    .getArticles(favorited = user?.username, offset = 0, limit = 10)
                                it(AppActions.FavoritesActions.SetFavorites(articles.articles))
                            }
                            is AppActions.FavoritesActions.AddFav -> {
                                store.enviroment.factories.getFavoritesService()
                                    .createArticleFavorite("Token ${user?.token}", action.slug)
                            }
                            is AppActions.FavoritesActions.RemoveFav -> {
                                store.enviroment.factories.getFavoritesService()
                                    .deleteArticleFavorite("Token ${user?.token}", action.slug)
                            }
                            else -> dispatcher(action)
                        }
                    }
                }
            }
        }
    }

val middlewareLogger: Middleware<AppState, AppActions, AppEnviroment> =
    {
        { store ->
            { dispatcher ->
                { action ->
                    withContext(Dispatchers.IO) {
                        Log.d(
                            "middlewareLogger",
                            "action: ${action::class.simpleName} page: ${store.state().feed.page} user: ${store.state().user}"
                        )
                        dispatcher(action)
                    }
                }
            }
        }
    }