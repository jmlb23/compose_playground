package com.github.jmlb23.mediumclone.state

import com.github.jmlb23.mediumclone.data.Factories
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope


data class AppEnviroment(val factories: Factories, val scope: CoroutineScope, val jsonSerializer: Gson)