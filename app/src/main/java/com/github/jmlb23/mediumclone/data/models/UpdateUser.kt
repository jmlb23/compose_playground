package com.github.jmlb23.mediumclone.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUser(
    var email: String? = null,
    var token: String? = null,
    var username: String? = null,
    var bio: String? = null,
    var image: String? = null
)
