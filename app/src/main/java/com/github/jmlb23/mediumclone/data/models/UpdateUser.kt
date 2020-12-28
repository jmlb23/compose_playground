package com.github.jmlb23.mediumclone.data.models

data class UpdateUser(
    var email: String? = null,
    var token: String? = null,
    var username: String? = null,
    var bio: String? = null,
    var image: String? = null
)
