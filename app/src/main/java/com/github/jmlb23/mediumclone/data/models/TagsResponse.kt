package com.github.jmlb23.mediumclone.data.models

import kotlinx.serialization.Serializable

@Serializable
data class TagsResponse(
      var tags: List<String>
)
