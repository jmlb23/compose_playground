package com.github.jmlb23.mediumclone

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ambientOf
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.github.jmlb23.mediumclone.state.AppActions
import com.github.jmlb23.mediumclone.state.AppEnviroment
import com.github.jmlb23.mediumclone.state.AppState
import com.github.jmlb23.mediumclone.state.Store
import kotlinx.coroutines.CoroutineScope

val AmbientCoroutineScope: ProvidableCompositionLocal<CoroutineScope> =
    compositionLocalOf { error("Not Provided CoroutineScope") }
val AmbientNavHostController: ProvidableCompositionLocal<NavHostController> =
    compositionLocalOf { error("Not Provided NavHostController") }
val AmbientStore: ProvidableCompositionLocal<Store<AppState, AppActions,AppEnviroment>> =
    compositionLocalOf { error("Not Provided Store") }

