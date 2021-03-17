package com.github.jmlb23.mediumclone.screens.feed.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.github.jmlb23.mediumclone.data.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FeedList(feeds: List<Article>, controller: NavHostController, add: suspend () -> Unit) {
    val scope = rememberCoroutineScope()
    LazyColumn {
        items(feeds.size) {
            Box(modifier = Modifier.clickable(onClick = {
                controller.navigate("/feed/${feeds[it].slug}")
            })) {
                FeedItem(art = feeds[it], controller)
            }
        }
        //TODO Temporal until fix scroll listener
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(all = 5.dp)) {
                Button(onClick = {
                    scope.launch(Dispatchers.IO) {
                        add()
                    }
                }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(text = "Load more")
                }
            }
        }
    }
}