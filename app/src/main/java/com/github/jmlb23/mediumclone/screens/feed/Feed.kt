package com.github.jmlb23.mediumclone.screens.feed

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.github.jmlb23.mediumclone.Ambients.LocalStore
import com.github.jmlb23.mediumclone.screens.feed.components.FeedList
import com.github.jmlb23.mediumclone.state.AppActions
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun Feed(controller: NavHostController) {
    val store = LocalStore.current
    val feed = store
        .select { it.feed.article }.distinctUntilChanged()
        .collectAsState(initial = emptyList())

    val scope = rememberCoroutineScope()

    FeedList(feeds = feed.value, controller = controller) {
        scope.launch {
            store.dispatch(AppActions.FeedActions.ChangePageAction)
            store.dispatch(AppActions.FeedActions.GetPages)
        }
    }

}