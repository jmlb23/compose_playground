package com.github.jmlb23.mediumclone.data.apis

import retrofit2.http.DELETE
import retrofit2.http.Headers
import retrofit2.http.POST
import com.github.jmlb23.mediumclone.data.models.*


@JvmSuppressWildcards
interface FavoritesApi {

    @Headers(
        "X-Operation-ID: CreateArticleFavorite",
      "Content-Type: application/json"
    )
    @POST("articles/{slug}/favorite")
    suspend fun createArticleFavorite(
        @retrofit2.http.Path("slug") slug: String
    ): SingleArticleResponse

    @Headers(
        "X-Operation-ID: DeleteArticleFavorite",
      "Content-Type: application/json"
    )
    @DELETE("articles/{slug}/favorite")
    suspend fun deleteArticleFavorite(
        @retrofit2.http.Path("slug") slug: String
    ): SingleArticleResponse
}
