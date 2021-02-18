package com.github.jmlb23.mediumclone.state

import android.util.Log
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

val middlewareLogger: Middleware<AppState, AppActions, AppEnviroment> =
    {
        { store ->
            { dispatcher ->
                { action ->
                    withContext(Dispatchers.IO) {
                        Log.d(
                            "middlewareLogger",
                            "action: ${action::class.simpleName} page: ${store.state().feed.page}"
                        )
                        dispatcher(action)
                    }
                }
            }
        }
    }