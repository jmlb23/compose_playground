package com.github.jmlb23.mediumclone.screens.feed

import androidx.compose.runtime.*
import com.github.jmlb23.mediumclone.AmbientCoroutineScope
import com.github.jmlb23.mediumclone.AmbientStore
import com.github.jmlb23.mediumclone.AppEvent
import com.github.jmlb23.mediumclone.screens.feed.components.FeedList
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun Feed() {
    val coroutineScope = AmbientCoroutineScope.current
    val store = AmbientStore.current
    val feed = store.state.map { it.articles }.distinctUntilChanged().collectAsState(initial = emptyList())

    onActive{
        val job = coroutineScope.launch {
            store.dispatch(AppEvent.ChangePageEvent)
        }
        onDispose {
            job.cancel()
        }
    }
    FeedList(feeds = feed.value){
        coroutineScope.launch {
            store.dispatch(AppEvent.ChangePageEvent)
        }
    }

}