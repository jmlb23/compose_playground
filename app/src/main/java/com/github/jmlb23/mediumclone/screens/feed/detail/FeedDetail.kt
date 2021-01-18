package com.github.jmlb23.mediumclone.screens.feed.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.github.jmlb23.mediumclone.AmbientCoroutineScope
import com.github.jmlb23.mediumclone.AmbientStore
import com.github.jmlb23.mediumclone.state.AppActions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


@Composable
fun FeedDetail(slug: String?){
    val store = AmbientStore.current
    val coroutineContext = AmbientCoroutineScope.current
    val article = store.select { it.detail.article }.flowOn(Dispatchers.IO).collectAsState(initial = null)
    val comments = store.select { it.detail.comments }.flowOn(Dispatchers.IO).collectAsState(initial = emptyList())

    onCommit{
        if (slug != null) {
            val job = coroutineContext.launch {
                store.dispatch(AppActions.DetailActions.GetDetail(slug))
            }

            onDispose {
                job.cancel()
            }
        }
    }
    Box {
        Text(text = article.value.toString())
        Text(text = comments.value.toString())
    }
}


@Preview
@Composable
fun Preview_FeedDetail(){
    FeedDetail(slug = "example")
}