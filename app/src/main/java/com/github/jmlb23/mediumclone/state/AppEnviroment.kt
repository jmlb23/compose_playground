package com.github.jmlb23.mediumclone.state

import com.github.jmlb23.mediumclone.data.Factories
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json


data class AppEnviroment(val factories: Factories, val scope: CoroutineScope, val jsonSerializer: Json)