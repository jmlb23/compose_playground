package com.github.jmlb23.mediumclone.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.github.jmlb23.mediumclone.Ambients
import com.github.jmlb23.mediumclone.component.LoginGuard
import com.github.jmlb23.mediumclone.screens.favs.Favorites
import com.github.jmlb23.mediumclone.screens.feed.Feed
import com.github.jmlb23.mediumclone.screens.feed.detail.FeedDetail
import com.github.jmlb23.mediumclone.screens.login.Login
import com.github.jmlb23.mediumclone.state.AppActions
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun Home() {
    val controller = rememberNavController()
    val backstack by controller.currentBackStackEntryAsState()
    val curentRoute = backstack?.arguments?.getString(KEY_ROUTE).toString()
    val store = Ambients.LocalStore.current

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .then(Modifier.fillMaxHeight(1f)),
        verticalArrangement = Arrangement.Bottom
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .weight(8f, true)
        ) {
            NavHost(navController = controller, startDestination = "/feed") {

                composable("/feed/{slug}") {
                    FeedDetail(
                        it.arguments?.getString("slug") ?: "",
                        controller
                    )
                }
                composable("/feed") {
                    Feed(controller)
                }
                composable("/favorites") {
                    LoginGuard(nav = controller) {
                        Favorites(controller)
                    }
                }
                composable("/example3") {
                    LoginGuard(nav = controller) {
                        Text("example4", color = Color.Black)
                    }
                }
                composable("/example4") {
                    LoginGuard(nav = controller) {
                        Text("example4", color = Color.Black)
                    }
                }
                composable("/profile") {
                    val token = store.select { it.user?.token }.collectAsState(initial = null)
                    token.value?.let {
                        Text("profile", color = Color.Black)
                    } ?: run {
                        Login()
                    }
                }
            }
        }
        BottomNavigation {
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Home, "Home") },
                selected = curentRoute == "/feed",
                onClick = { controller.navigate("/feed") })
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Favorite, "Favs") },
                selected = curentRoute == "/favorites",
                onClick = { controller.navigate("/favorites") })
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Add, "Add") },
                selected = curentRoute == "/example3",
                onClick = { controller.navigate("/example3") })
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Notifications, "Notifications") },
                selected = curentRoute == "/example4",
                onClick = { controller.navigate("/example4") })
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Person, "Person") },
                selected = curentRoute == "/profile",
                onClick = { controller.navigate("/profile") })
        }
    }
}


@Preview("Home")
@Composable
fun PreviewHome() {
    Home()
}