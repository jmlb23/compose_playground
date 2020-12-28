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
import com.github.jmlb23.mediumclone.ui.MediumCloneTheme
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediumCloneTheme {
                val controller = rememberNavController()
                Providers(
                    AmbientNavHostController provides controller,
                    AmbientCoroutineScope provides lifecycleScope,
                    AmbientStore provides createAsyncStore(
                        def = AppState(0, emptyList()),
                        reducer = { state, action, env ->
                            action.flatMapLatest {
                                when (it) {
                                    AppEvent.ChangePageEvent -> state.map {
                                        it.copy(
                                            page = it.page + 1,
                                            articles = it.articles + env.ariclesS.getArticles(
                                                limit = 10,
                                                offset = it.page * 10
                                            ).articles
                                        )
                                    }
                                    else -> state
                                }
                            }
                        },
                        env = AppEnviroment(Factories.getArticlesService())
                    )
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
