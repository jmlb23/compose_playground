package com.github.jmlb23.mediumclone.screens.feed.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.github.jmlb23.mediumclone.Ambients.LocalStore
import com.github.jmlb23.mediumclone.data.models.Article
import com.github.jmlb23.mediumclone.state.AppActions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FeedList(feeds: List<Article>, controller: NavHostController, add: suspend () -> Unit) {
    val scope = rememberCoroutineScope()
    val store = LocalStore.current
    //TODO save position on store best practice?
    val position = store.select { it.feed.position }.collectAsState(initial = 0)

    LazyColumn(state = LazyListState(firstVisibleItemIndex = position.value)) {
        items(feeds.size) {
            Box(modifier = Modifier.clickable(onClick = {
                scope.launch {
                    store.dispatch(AppActions.FeedActions.SetPositionAction(it))
                }
                controller.navigate("/feed/${feeds[it].slug}")
            })) {
                FeedItem(art = feeds[it], controller)
            }
        }
        //TODO Temporal until fix scroll listener
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 5.dp)
            ) {
                Button(onClick = {
                    scope.launch(Dispatchers.IO) {
                        store.dispatch(AppActions.FeedActions.SetPositionAction((feeds
                            .size
                            .takeIf { it > 0 }
                            ?.let { it + 10 } ?: 0)) )
                        add()
                    }
                }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(text = "Load more")
                }
            }
        }
    }
}