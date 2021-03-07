package com.github.jmlb23.mediumclone.data.models

import com.github.jmlb23.mediumclone.data.models.Comment
import kotlinx.serialization.Serializable

@Serializable
data class MultipleCommentsResponse(
      var comments: List<Comment>
)
