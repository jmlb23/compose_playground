package com.github.jmlb23.mediumclone.screens.favs

import androidx.compose.runtime.*
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
    val feed = store
        .select { it.favorites.article }.distinctUntilChanged()
        .collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()


    FeedList(feeds = feed.value, controller = controller) {
        scope.launch {
            store.dispatch(AppActions.FavoritesActions.GetFavorites)
        }
    }
}