package com.github.jmlb23.mediumclone

import androidx.compose.runtime.ProvidableAmbient
import androidx.compose.runtime.ambientOf
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

val AmbientCoroutineScope: ProvidableAmbient<CoroutineScope> = ambientOf { error("Not Provided CoroutineScope") }
val AmbientNavHostController: ProvidableAmbient<NavHostController> = ambientOf { error("Not Provided NavHostController") }
val AmbientStore: ProvidableAmbient<AsyncStore<AppState,AppEvent,AppEnviroment>> = ambientOf { error("Not Provided Store") }

