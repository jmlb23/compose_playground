package com.github.jmlb23.mediumclone.data.apis

import com.github.jmlb23.mediumclone.data.models.*
import com.github.jmlb23.mediumclone.data.models.LoginUserRequest
import retrofit2.http.*

@JvmSuppressWildcards
interface UserAndAuthenticationApi {

    @Headers(
        "X-Operation-ID: CreateUser",
      "Content-Type: application/json"
    )
    @POST("users")
    suspend fun createUser(
        @Body body: NewUserRequest
    ): UserResponse

    @Headers(
        "X-Operation-ID: GetCurrentUser",
      "Content-Type: application/json"
    )
    @GET("users")
    suspend fun getCurrentUser(): UserResponse

    @Headers(
        "X-Operation-ID: Login",
      "Content-Type: application/json"
    )
    @POST("users/login")
    suspend fun login(
        @Body body: LoginUserRequest
    ): UserResponse

    @Headers(
        "X-Operation-ID: UpdateCurrentUser",
      "Content-Type: application/json"
    )
    @PUT("users")
    suspend fun updateCurrentUser(
        @Body body: UpdateUserRequest
    ): UserResponse
}
