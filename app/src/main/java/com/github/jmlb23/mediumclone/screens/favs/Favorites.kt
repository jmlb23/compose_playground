package com.github.jmlb23.mediumclone.screens.favs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.github.jmlb23.mediumclone.Ambients
import com.github.jmlb23.mediumclone.screens.feed.components.FeedList
import com.github.jmlb23.mediumclone.state.AppActions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun Favorites(controller: NavHostController) {
    val store = Ambients.LocalStore.current
    val scope = rememberCoroutineScope()
    val feed = store
        .select { it.favorites.article }.distinctUntilChanged()
        .collectAsState(initial = emptyList())

    FeedList(feeds = feed.value, controller = controller) {
        DisposableEffect(feed){
            val job = scope.launch(Dispatchers.IO) {
                store.dispatch(AppActions.FavoritesActions.GetFavorites)
            }
            onDispose {
                job.cancel()
            }
        }
    }
}