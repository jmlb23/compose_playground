package com.github.jmlb23.mediumclone.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.github.jmlb23.mediumclone.screens.feed.Feed

@Composable
fun Home() {
    val controller = rememberNavController()
    Column (modifier = Modifier.fillMaxWidth(1f).then(Modifier.fillMaxHeight(1f)),verticalArrangement = Arrangement.Bottom) {
        Box(modifier = Modifier.fillMaxWidth(1f).weight(8f,true)){
            NavHost(navController = controller, startDestination = "/feed") {
                composable("/feed") { Feed() }
                composable("/example2") { Text("example2", color = Color.Black) }
                composable("/example3") { Text("example3", color = Color.Black) }
                composable("/example4") { Text("example4", color = Color.Black) }
            }
        }
        BottomNavigation {
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Home) },
                selected = true,
                onClick = { controller.navigate("/feed") })
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Favorite) },
                selected = false,
                onClick = { controller.navigate("/example2") })
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Add) },
                selected = false,
                onClick = { controller.navigate("/example3") })
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Notifications) },
                selected = false,
                onClick = { controller.navigate("/example4") })
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Person) },
                selected = false,
                onClick = { controller.navigate("/example4") })
        }
    }

}


@Preview("Home")
@Composable
fun PreviewHome(){
    Home()
}