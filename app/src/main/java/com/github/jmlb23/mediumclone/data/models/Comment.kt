package com.github.jmlb23.mediumclone.data.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
      var id: Int,
      var createdAt: String,
      var updatedAt: String,
      var body: String,
      var author: Profile
)
