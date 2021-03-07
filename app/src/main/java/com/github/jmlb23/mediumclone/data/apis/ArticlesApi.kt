package com.github.jmlb23.mediumclone.data.apis

import com.github.jmlb23.mediumclone.data.models.*
import retrofit2.http.*

interface ArticlesApi {

    @Headers(
      "Content-Type: application/json"
    )
    @POST("articles")
    suspend fun createArticle(
        @Body article: NewArticleRequest
    ): SingleArticleResponse

    @Headers(
      "Content-Type: application/json"
    )
    @DELETE("articles/{slug}")
    suspend fun deleteArticle(
        @Path("slug") slug: String
    ): Unit

    @Headers(
      "Content-Type: application/json"
    )
    @GET("articles/{slug}")
    suspend fun getArticle(
        @Path("slug") slug: String
    ): SingleArticleResponse

    @Headers(
      "Content-Type: application/json"
    )
    @GET("articles")
    suspend fun getArticles(
        @Query("tag") tag: String? = null,
        @Query("author") author: String? = null,
        @Query("favorited") favorited: String? = null,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): MultipleArticlesResponse

    @Headers(
      "Content-Type: application/json"
    )
    @GET("articles/feed")
    suspend fun getArticlesFeed(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): MultipleArticlesResponse

    @Headers(
      "Content-Type: application/json"
    )
    @PUT("articles/{slug}")
    suspend fun updateArticle(
        @Path("slug") slug: String,
        @Body article: UpdateArticleRequest
    ): SingleArticleResponse
}
