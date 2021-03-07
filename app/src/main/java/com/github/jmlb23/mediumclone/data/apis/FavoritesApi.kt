package com.github.jmlb23.mediumclone.data.apis

import com.github.jmlb23.mediumclone.data.models.*
import retrofit2.http.*


interface FavoritesApi {

    @POST("articles/{slug}/favorite")
    suspend fun createArticleFavorite(
        @Header("Authorization") token: String,
        @Path("slug") slug: String
    ): SingleArticleResponse

    @DELETE("articles/{slug}/favorite")
    suspend fun deleteArticleFavorite(
        @Header("Authorization") token: String,
        @Path("slug") slug: String
    ): SingleArticleResponse
}
