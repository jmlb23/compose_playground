package com.github.jmlb23.mediumclone.data.apis

import com.github.jmlb23.mediumclone.data.models.*
import com.github.jmlb23.mediumclone.data.models.LoginUserRequest
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

@JvmSuppressWildcards
interface UserAndAuthenticationApi {

    @Headers(
        "X-Operation-ID: CreateUser",
      "Content-Type: application/json"
    )
    @POST("users")
    suspend fun createUser(
        @retrofit2.http.Body body: NewUserRequest
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
        @retrofit2.http.Body body: LoginUserRequest
    ): UserResponse

    @Headers(
        "X-Operation-ID: UpdateCurrentUser",
      "Content-Type: application/json"
    )
    @PUT("users")
    suspend fun updateCurrentUser(
        @retrofit2.http.Body body: UpdateUserRequest
    ): UserResponse
}
