package com.github.jmlb23.mediumclone

import com.github.jmlb23.mediumclone.data.apis.ArticlesApi
import com.github.jmlb23.mediumclone.data.models.Article

data class AppState(val page: Int, val articles: List<Article>)

sealed class AppEvent {
	object ChangePageEvent : AppEvent()
}

data class AppEnviroment(val ariclesS: ArticlesApi)


