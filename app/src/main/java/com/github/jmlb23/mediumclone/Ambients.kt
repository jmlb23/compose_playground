package com.github.jmlb23.mediumclone

import androidx.compose.runtime.ProvidableAmbient
import androidx.compose.runtime.ambientOf
import androidx.navigation.NavHostController
import com.github.jmlb23.mediumclone.state.AppActions
import com.github.jmlb23.mediumclone.state.AppEnviroment
import com.github.jmlb23.mediumclone.state.AppState
import com.github.jmlb23.mediumclone.state.Store
import kotlinx.coroutines.CoroutineScope

val AmbientCoroutineScope: ProvidableAmbient<CoroutineScope> = ambientOf { error("Not Provided CoroutineScope") }
val AmbientNavHostController: ProvidableAmbient<NavHostController> = ambientOf { error("Not Provided NavHostController") }
val AmbientStore: ProvidableAmbient<Store<AppState, AppActions,AppEnviroment>> = ambientOf { error("Not Provided Store") }

