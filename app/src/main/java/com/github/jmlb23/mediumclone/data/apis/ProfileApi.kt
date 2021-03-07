package com.github.jmlb23.mediumclone.data.apis

import com.github.jmlb23.mediumclone.data.models.*
import retrofit2.http.*


@JvmSuppressWildcards
interface ProfileApi {

    @Headers(
        "X-Operation-ID: FollowUserByUsername",
      "Content-Type: application/json"
    )
    @POST("profiles/{username}/follow")
    suspend fun followUserByUsername(
        @Path("username") username: String
    ): ProfileResponse

    @Headers(
        "X-Operation-ID: GetProfileByUsername",
      "Content-Type: application/json"
    )
    @GET("profiles/{username}")
    suspend fun getProfileByUsername(
        @Path("username") username: String
    ): ProfileResponse

    @Headers(
        "X-Operation-ID: UnfollowUserByUsername",
      "Content-Type: application/json"
    )
    @DELETE("profiles/{username}/follow")
    suspend fun unfollowUserByUsername(
        @Path("username") username: String
    ): ProfileResponse
}
