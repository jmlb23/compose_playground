package com.github.jmlb23.mediumclone.data.apis

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import com.github.jmlb23.mediumclone.data.models.*


@JvmSuppressWildcards
interface ProfileApi {

    @Headers(
        "X-Operation-ID: FollowUserByUsername",
      "Content-Type: application/json"
    )
    @POST("profiles/{username}/follow")
    suspend fun followUserByUsername(
        @retrofit2.http.Path("username") username: String
    ): ProfileResponse

    @Headers(
        "X-Operation-ID: GetProfileByUsername",
      "Content-Type: application/json"
    )
    @GET("profiles/{username}")
    suspend fun getProfileByUsername(
        @retrofit2.http.Path("username") username: String
    ): ProfileResponse

    @Headers(
        "X-Operation-ID: UnfollowUserByUsername",
      "Content-Type: application/json"
    )
    @DELETE("profiles/{username}/follow")
    suspend fun unfollowUserByUsername(
        @retrofit2.http.Path("username") username: String
    ): ProfileResponse
}
