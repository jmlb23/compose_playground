package com.github.jmlb23.mediumclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Providers
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.jmlb23.mediumclone.data.Factories
import com.github.jmlb23.mediumclone.screens.home.Home
import com.github.jmlb23.mediumclone.screens.splash.Splash
import com.github.jmlb23.mediumclone.state.*
import com.github.jmlb23.mediumclone.ui.MediumCloneTheme
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.plus
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val scope = lifecycleScope + CoroutineExceptionHandler({ ctx, ex -> throw  ex })
        val store = createStore(
            initalState = AppState(),
            appEnviroment = AppEnviroment(Factories, scope = scope, jsonSerializer = GsonBuilder().setPrettyPrinting().create()),
            reducer = ::mainReducer,
            middleware = combineMiddlewares(::middlewareLogger,::middlewarePagination,
                ::middlewareDetailArticle, ::middlewareDetailComment
            )
        )

        super.onCreate(savedInstanceState)
        setContent {
            MediumCloneTheme {
                val controller = rememberNavController()
                Providers(
                    AmbientNavHostController provides controller,
                    AmbientCoroutineScope provides scope,
                    AmbientStore provides store
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
