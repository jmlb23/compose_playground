package com.github.jmlb23.mediumclone.state

fun mainReducer(state: AppState, actions: AppActions): AppState =
    state.copy(
        feed = reducerScreenData(
            state.feed,
            actions
        ),
        detail = reducerDetailData(
            state.detail,
            actions
        )
    )

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