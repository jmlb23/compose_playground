package com.github.jmlb23.mediumclone.data.apis

import retrofit2.http.GET
import retrofit2.http.Headers
import com.github.jmlb23.mediumclone.data.models.*


@JvmSuppressWildcards
interface DefaultApi {
    /**
     * Get tags
     * Get tags. Auth not required
     * The endpoint is owned by defaultname service owner
     */
    @Headers(
        "Content-Type: application/json"
    )
    @GET("tags")
    suspend fun tagsGet(): TagsResponse
}
