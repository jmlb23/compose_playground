package com.github.jmlb23.mediumclone.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
      var username: String?,
      var bio: String?,
      var image: String?,
      var following: Boolean
)
