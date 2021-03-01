package com.github.jmlb23.mediumclone.state

import com.github.jmlb23.mediumclone.data.models.User

fun mainReducer(state: AppState, actions: AppActions): AppState =
    state.copy(
        feed = reducerScreenData(
            state.feed,
            actions
        ),
        detail = reducerDetailData(
            state.detail,
            actions
        ),
        user = reducerUserData(
            state.user,
            actions
        ),
        favorites = reducerFavState(
            state.favorites,
            actions
        )
    )

fun reducerFavState(partialState: FavState, actions: AppActions): FavState {
    return when (actions) {
        is AppActions.FavoritesActions.SetFavorites -> {
            partialState.copy(article = actions.favs)
        }
        else -> partialState
    }
}

fun reducerUserData(state: User?, actions: AppActions): User? {
    return when (actions) {
        is AppActions.LoginActions.SetCurrentUser -> {
            actions.user
        }
        else -> state
    }
}

fun reducerScreenData(partialState: FeedState, actions: AppActions): FeedState =
    when (actions) {
        AppActions.FeedActions.ChangePageAction -> {
            val old = partialState.page
            val new = old + 1
            partialState.copy(page = new)
        }
        is AppActions.FeedActions.SetPagesAction -> partialState.copy(article = partialState.article + (actions.value))
        else -> partialState
    }

fun reducerDetailData(partialState: DetailState, actions: AppActions): DetailState =
    when (actions) {
        is AppActions.DetailActions.SetComments -> partialState.copy(comments = partialState.comments + actions.value)
        is AppActions.DetailActions.SetArticle -> partialState.copy(article = actions.article)
        else -> partialState
    }