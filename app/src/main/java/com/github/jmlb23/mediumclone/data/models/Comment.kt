package com.github.jmlb23.mediumclone.data.models

import kotlinx.datetime.LocalDateTime


data class Comment(
      var id: Int,
      var createdAt: LocalDateTime,
      var updatedAt: LocalDateTime,
      var body: String,
      var author: Profile
)
