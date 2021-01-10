package com.github.jmlb23.mediumclone.state

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


fun <S, A, E> combineMiddlewares(vararg middleware: suspend (Store<S, A, E>, suspend (A) -> Unit, A) -> Unit): suspend (Store<S, A, E>, suspend (A) -> Unit, A) -> Unit {
    return middleware.iterator().asSequence()
        .reduce { acc, new ->
            { store: Store<S, A, E>, next: suspend (A) -> Unit, a: A ->
                acc(store,{ new(store,next,it) },a)
            }
        }
}

suspend fun middlewarePagination(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> Unit,
    action: AppActions
): Unit = withContext(Dispatchers.IO) {
    val environment = store.enviroment
    val old = store.getState()
    when (action) {
        AppActions.FeedActions.ChangePageAction -> {
            Log.d("MIDDLEWARE_API", "1")
            val pages = environment.factories.getArticlesService()
                .getArticles(limit = 10, offset = (old.feed?.page ?: 0) * 10)
            dispatcher(AppActions.FeedActions.SetPagesAction(pages.articles))
        }
        else -> Unit
    }
}

suspend fun middlewareLogger(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> Unit,
    action: AppActions
) {
    val environment = store.enviroment
    val state = store.getState()
    Log.d("MIDDLEWARE_LOGGER", "state: ${environment.jsonSerializer.toJson(state)}")
    dispatcher(action)
}
