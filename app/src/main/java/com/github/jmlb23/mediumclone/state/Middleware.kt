package com.github.jmlb23.mediumclone.state

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

fun <S, A, E> Middleware<S, A, E>.decompose(): (Store<S, A, E>) -> (suspend (A) -> Unit) -> (A) -> Job =
    { store: Store<S, A, E> ->
        { next: suspend (A) -> Unit ->
            { a: A ->
                this(store, next, a)
            }
        }
    }

fun <S, A, E> Middleware<S, A, E>.combine(middleware: Middleware<S, A, E>): Middleware<S, A, E> =
    { store: Store<S, A, E>, next: suspend (A) -> Unit, a: A ->
        this(store, { middleware.decompose()(store)(next)(it) }, a)
    }

fun <S, A, E> applyMiddleware(
    vararg middleware: Middleware<S, A, E>
): Middleware<S, A, E> {
    return middleware.iterator().asSequence().reduce { acc, new ->
        acc.combine(new)
    }

}

fun middlewarePagination(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> Unit,
    action: AppActions
) = store.enviroment.scope.launch(Dispatchers.IO) {
    Log.d("MIDDLEWARE_PAGINATION", Date().toString())
    when (action) {
        AppActions.FeedActions.ChangePageAction -> {
            val environment = store.enviroment
            val old = store.getState()
            val pages = environment.factories.getArticlesService()
                .getArticles(limit = 10, offset = (old.feed?.page ?: 0) * 10)
            store.dispatch(AppActions.FeedActions.SetPagesAction(pages.articles))
        }
        else -> dispatcher(action)
    }
}

fun middlewareDetailArticle(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> Unit,
    action: AppActions
) = store.enviroment.scope.launch(Dispatchers.IO) {
    Log.d("MIDDLEWARE_DET", Date().toString())
    val environment = store.enviroment
    when (action) {
        is AppActions.DetailActions.GetDetail -> {
            val pages = environment.factories.getArticlesService()
                .getArticle(action.slug)
            store.dispatch(AppActions.DetailActions.SetArticle(pages.article))
        }
        else -> dispatcher(action)
    }
}

fun middlewareDetailComment(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> Unit,
    action: AppActions
) = store.enviroment.scope.launch(Dispatchers.IO) {
    Log.d("MIDDLEWARE_DET_COMMENTS", Date().toString())
    val environment = store.enviroment
    when (action) {
        is AppActions.DetailActions.GetDetail -> {
            val pages = environment.factories.getCommentsService()
                .getArticleComments(action.slug)
            store.dispatch(AppActions.DetailActions.SetComments(pages.comments))
        }
        else -> dispatcher(action)
    }
}

fun middlewareLogger(
    store: Store<AppState, AppActions, AppEnviroment>,
    dispatcher: suspend (AppActions) -> Unit,
    action: AppActions
) = store.enviroment.scope.launch(Dispatchers.Main) {
    Log.d("MIDDLEWARE_LOGGER", "action: ${Date()} ${store.getState().feed?.page}")
    dispatcher(action)
}
