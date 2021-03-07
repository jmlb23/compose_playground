package com.github.jmlb23.mediumclone.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginUser(
      var email: String,
      var password: String
)
