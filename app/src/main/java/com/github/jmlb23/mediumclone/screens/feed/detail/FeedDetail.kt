package com.github.jmlb23.mediumclone.screens.feed.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.jmlb23.mediumclone.Ambients.LocalStore
import com.github.jmlb23.mediumclone.screens.feed.components.CommentItem
import com.github.jmlb23.mediumclone.state.AppActions
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


@Composable
fun FeedDetail(slug: String, navigator: NavHostController) {
    val store = LocalStore.current
    val coroutineContext = rememberCoroutineScope()
    val article =
        store.select { it.detail.article }.flowOn(Dispatchers.IO).collectAsState(initial = null)
    val comments = store.select { it.detail.comments }.flowOn(Dispatchers.IO)
        .collectAsState(initial = emptyList())


    DisposableEffect(key1 = null) {
        val job = coroutineContext.launch {
            store.dispatch(AppActions.DetailActions.GetDetail(slug))
        }
        onDispose {
            job.cancel()
        }
    }
    Column {
        TopAppBar({ Icon(Icons.Default.ArrowBack, "Back", modifier = Modifier.clickable { navigator.popBackStack() }) })
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            item {
                Column {
                    Row {
                        CoilImage(
                            data = article.value?.author?.image ?: "",
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .border(
                                    shape = CircleShape,
                                    border = BorderStroke(1.dp, Color.Transparent)
                                )
                                .size(40.dp, 40.dp)
                                .clip(CircleShape)
                                .border(BorderStroke(2.dp, Color.Black), shape = CircleShape),
                            contentScale = ContentScale.Fit
                        )
                        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                            Text(text = article.value?.author?.username ?: "")
                            Text(text = article.value?.createdAt ?: "")
                        }
                    }
                    Text(text = article.value?.title ?: "", style = MaterialTheme.typography.h3)
                    Text(text = article.value?.body ?: "", style = MaterialTheme.typography.body1)
                    this@LazyColumn.items(comments.value.size){
                        CommentItem(comment = comments.value[it])
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun Preview_FeedDetail() {
    FeedDetail(slug = "example", rememberNavController())
}