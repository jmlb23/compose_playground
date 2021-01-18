package com.github.jmlb23.mediumclone.state

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

suspend fun middlewarePagination(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> Unit,
    action: AppActions
) {
    Log.d("MIDDLEWARE_PAGINATION", Date().toString())
    return when (action) {
        AppActions.FeedActions.ChangePageAction -> {
            val environment = store.enviroment
            val old = store.getState()
            val pages = environment.factories.getArticlesService()
                .getArticles(limit = 10, offset = (old.feed.page ?: 0) * 10)
            store.dispatch(AppActions.FeedActions.SetPagesAction(pages.articles))
        }
        else -> dispatcher(action)
    }
}

suspend fun middlewareDetailArticle(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> Unit,
    action: AppActions
) {
    Log.d("MIDDLEWARE_DET", Date().toString())
    val environment = store.enviroment
    return when (action) {
        is AppActions.DetailActions.GetDetail -> {
            val pages = environment.factories.getArticlesService()
                .getArticle(action.slug)
            store.dispatch(AppActions.DetailActions.SetArticle(pages.article))
        }
        else -> dispatcher(action)
    }

}

suspend fun middlewareDetailComment(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> Unit,
    action: AppActions
) {
    Log.d("MIDDLEWARE_DET_COMMENTS", Date().toString())
    val environment = store.enviroment
    return when (action) {
        is AppActions.DetailActions.GetDetail -> {
            val pages = environment.factories.getCommentsService()
                .getArticleComments(action.slug)
            store.dispatch(AppActions.DetailActions.SetComments(pages.comments))
        }
        else -> dispatcher(action)
    }
}

suspend fun middlewareLogger(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> Unit,
    action: AppActions
) {
    Log.d("MIDDLEWARE_LOGGER", "action: ${Date()} ${action}")
    return dispatcher(action)
}
