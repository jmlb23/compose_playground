package com.github.jmlb23.mediumclone.screens.feed.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.jmlb23.mediumclone.AmbientCoroutineScope
import com.github.jmlb23.mediumclone.AmbientStore
import com.github.jmlb23.mediumclone.state.AppActions
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


@Composable
fun FeedDetail(slug: String) {
    val store = AmbientStore.current
    val coroutineContext = AmbientCoroutineScope.current
    val article =
        store.select { it.detail.article }.flowOn(Dispatchers.IO).collectAsState(initial = null)
    val comments = store.select { it.detail.comments }.flowOn(Dispatchers.IO)
        .collectAsState(initial = emptyList())

    onCommit {
        val job = coroutineContext.launch {
            store.dispatch(AppActions.DetailActions.GetDetail(slug))
        }
        onDispose {
            job.cancel()
        }
    }
    Box {
        Column {
            CoilImage(
                data = article.value?.author?.image ?: "",
                modifier = Modifier
                    .border(
                        shape = CircleShape,
                        border = BorderStroke(1.dp, Color.Transparent)
                    )
                    .padding(10.dp)
                    .size(40.dp, 40.dp)
                    .clip(CircleShape)
                    .border(BorderStroke(2.dp, Color.Black), shape = CircleShape),
                contentScale = ContentScale.Fit
            )
            Text(text = article.value.toString())
            Text(text = comments.value.toString())
        }
    }
}


@Preview
@Composable
fun Preview_FeedDetail() {
    FeedDetail(slug = "example")
}