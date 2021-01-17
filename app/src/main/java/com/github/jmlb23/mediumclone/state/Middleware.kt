package com.github.jmlb23.mediumclone.state

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


fun <S, A, E> combineMiddlewares(
    vararg middleware: suspend (Store<S, A, E>, suspend (A) -> A, A) -> A
): suspend (Store<S, A, E>, suspend (A) -> A, A) -> A {
    return middleware.iterator().asSequence()
        .reduce { acc, new ->
            { store: Store<S, A, E>, next: suspend (A) -> A, a: A ->
                acc(store,{ new(store,next,it) },a)
            }
        }
}

suspend fun middlewarePagination(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> AppActions,
    action: AppActions
): AppActions = withContext(Dispatchers.IO) {
    val environment = store.enviroment
    val old = store.getState()
    when (action) {
        AppActions.FeedActions.ChangePageAction -> {
            val pages = environment.factories.getArticlesService()
                .getArticles(limit = 10, offset = (old.feed?.page ?: 0) * 10)
            return@withContext dispatcher(AppActions.FeedActions.SetPagesAction(pages.articles))
        }
        else -> return@withContext dispatcher(action)
    }
}

suspend fun middlewareDetailArticle(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> AppActions,
    action: AppActions
): AppActions = withContext(Dispatchers.IO) {
    val environment = store.enviroment
    when (action) {
        is AppActions.DetailActions.GetDetail -> {
            val pages = environment.factories.getArticlesService()
                .getArticle(action.slug)
            return@withContext dispatcher(AppActions.DetailActions.SetArticle(pages.article))
        }
        else -> return@withContext dispatcher(action)
    }
}

suspend fun middlewareDetailComment(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> AppActions,
    action: AppActions
): AppActions = withContext(Dispatchers.IO) {
    val environment = store.enviroment
    when (action) {
        is AppActions.DetailActions.GetDetail -> {
            val pages = environment.factories.getCommentsService()
                .getArticleComments(action.slug)
            return@withContext dispatcher(AppActions.DetailActions.SetComments(pages.comments))
        }
        else -> return@withContext dispatcher(action)
    }
}

suspend fun middlewareLogger(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> AppActions,
    action: AppActions
) : AppActions{
    Log.d("MIDDLEWARE_LOGGER", "action: ${action.toString()}")
    return dispatcher(action)
}
