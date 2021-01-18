package com.github.jmlb23.mediumclone.screens.feed

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.github.jmlb23.mediumclone.AmbientCoroutineScope
import com.github.jmlb23.mediumclone.AmbientStore
import com.github.jmlb23.mediumclone.screens.feed.components.FeedList
import com.github.jmlb23.mediumclone.state.AppActions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@Composable
fun Feed(controller: NavHostController) {
    val scope = AmbientCoroutineScope.current
    val store = AmbientStore.current
    val feed = store
        .select { it.feed.article.orEmpty() }.flowOn(Dispatchers.IO)
        .collectAsState(initial = emptyList(),scope.coroutineContext)

    FeedList(feeds = feed.value,controller = controller) {
        scope.launch {
            store.dispatch(AppActions.FeedActions.ChangePageAction)
        }
    }

}