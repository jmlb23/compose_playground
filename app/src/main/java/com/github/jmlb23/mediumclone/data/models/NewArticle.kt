package com.github.jmlb23.mediumclone.data.models

import kotlinx.serialization.Serializable

@Serializable
data class NewArticle(
      var title: String,
      var description: String,
      var body: String,
      var tagList: List<String>? = null
)
