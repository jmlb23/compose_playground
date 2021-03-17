package com.github.jmlb23.mediumclone

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.github.jmlb23.mediumclone.state.AppActions
import com.github.jmlb23.mediumclone.state.AppEnviroment
import com.github.jmlb23.mediumclone.state.AppState
import com.github.jmlb23.mediumclone.state.Store

object Ambients {
    val LocalNavHostController: ProvidableCompositionLocal<NavHostController> =
        compositionLocalOf { error("Not Provided NavHostController") }
    val LocalStore: ProvidableCompositionLocal<Store<AppState, AppActions, AppEnviroment>> =
        compositionLocalOf { error("Not Provided Store") }
}