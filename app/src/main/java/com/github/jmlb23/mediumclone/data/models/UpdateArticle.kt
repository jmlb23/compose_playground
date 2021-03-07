package com.github.jmlb23.mediumclone.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateArticle(
      var title: String? = null,
      var description: String? = null,
      var body: String? = null
)
