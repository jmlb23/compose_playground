package com.github.jmlb23.mediumclone.screens.feed

import android.util.Log
import androidx.compose.runtime.*
import com.github.jmlb23.mediumclone.AmbientCoroutineScope
import com.github.jmlb23.mediumclone.AmbientStore
import com.github.jmlb23.mediumclone.screens.feed.components.FeedList
import com.github.jmlb23.mediumclone.state.AppActions
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@Composable
fun Feed() {
    val scope = AmbientCoroutineScope.current
    val store = AmbientStore.current
    val feed = store
        .subcribe
        .map { it.feed?.article.orEmpty() }
        .collectAsState(initial = emptyList())

    onActive {
        val job = scope.launch {
            store.dispatch(AppActions.FeedActions.ChangePageAction)
        }

        onDispose {
            job.cancel()
        }
    }

    FeedList(feeds = feed.value) {
        scope.launch {
            store.dispatch(AppActions.FeedActions.ChangePageAction)
        }
    }

}