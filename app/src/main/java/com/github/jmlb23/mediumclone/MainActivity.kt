package com.github.jmlb23.mediumclone

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.jmlb23.mediumclone.Ambients.LocalCoroutineScope
import com.github.jmlb23.mediumclone.Ambients.LocalNavHostController
import com.github.jmlb23.mediumclone.Ambients.LocalStore
import com.github.jmlb23.mediumclone.data.Factories
import com.github.jmlb23.mediumclone.screens.home.Home
import com.github.jmlb23.mediumclone.screens.splash.Splash
import com.github.jmlb23.mediumclone.state.*
import com.github.jmlb23.mediumclone.ui.MediumCloneTheme
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val store = createStore(
            initalState = AppState(),
            appEnviroment = AppEnviroment(
                Factories,
                scope = lifecycleScope + CoroutineExceptionHandler({ ctx, ex -> throw  ex }),
                jsonSerializer = GsonBuilder().setPrettyPrinting().create()
            ),
            reducer = ::mainReducer,
            middleware =
            listOf(
                middlewarePagination,
                middlewareDetailArticle,
                middlewareDetailComment,
                middlewareLogger,
            )
        )
        setContent {
            MediumCloneTheme {
                val controller = rememberNavController()
                CompositionLocalProvider(
                    LocalNavHostController provides controller,
                    LocalCoroutineScope provides lifecycleScope + CoroutineExceptionHandler({ ctx, ex -> throw  ex }),
                    LocalStore provides store
                ) {
                    NavHost(navController = controller, startDestination = "/splash", builder = {
                        composable("/splash") { Splash() }
                        composable("/home") { Home() }
                    })
                }
            }

        }
    }

}
