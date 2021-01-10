package com.github.jmlb23.mediumclone.state


fun mainReducer(state: AppState, actions: AppActions): AppState =
    state.copy(
        feed = reducerScreenData(
            state.feed,
            actions as AppActions.FeedActions
        )
    )

fun reducerScreenData(partialState: FeedState?, actions: AppActions.FeedActions): FeedState?  = when(actions){
    AppActions.FeedActions.ChangePageAction -> partialState?.copy(page = if(partialState.page == null) 0 else partialState.page + 1)
    is AppActions.FeedActions.SetPagesAction -> partialState?.copy(article = partialState.article.orEmpty() + (actions.value))
    else -> partialState
}