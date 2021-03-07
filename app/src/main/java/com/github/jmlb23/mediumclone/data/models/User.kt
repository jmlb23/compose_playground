package com.github.jmlb23.mediumclone.data.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    var email: String,
    var token: String,
    var username: String,
    var bio: String,
    var image: String?
)
