package com.github.jmlb23.mediumclone.data.apis

import com.github.jmlb23.mediumclone.data.models.*
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface ArticlesApi {

    @Headers(
      "Content-Type: application/json"
    )
    @POST("articles")
    suspend fun createArticle(
        @retrofit2.http.Body article: NewArticleRequest
    ): SingleArticleResponse

    @Headers(
      "Content-Type: application/json"
    )
    @DELETE("articles/{slug}")
    suspend fun deleteArticle(
        @retrofit2.http.Path("slug") slug: String
    ): Unit

    @Headers(
      "Content-Type: application/json"
    )
    @GET("articles/{slug}")
    suspend fun getArticle(
        @retrofit2.http.Path("slug") slug: String
    ): SingleArticleResponse

    @Headers(
      "Content-Type: application/json"
    )
    @GET("articles")
    suspend fun getArticles(
        @retrofit2.http.Query("tag") tag: String? = null,
        @retrofit2.http.Query("author") author: String? = null,
        @retrofit2.http.Query("favorited") favorited: String? = null,
        @retrofit2.http.Query("limit") limit: Int?,
        @retrofit2.http.Query("offset") offset: Int?
    ): MultipleArticlesResponse

    @Headers(
      "Content-Type: application/json"
    )
    @GET("articles/feed")
    suspend fun getArticlesFeed(
        @retrofit2.http.Query("limit") limit: Int?,
        @retrofit2.http.Query("offset") offset: Int?
    ): MultipleArticlesResponse

    @Headers(
      "Content-Type: application/json"
    )
    @PUT("articles/{slug}")
    suspend fun updateArticle(
        @retrofit2.http.Path("slug") slug: String,
        @retrofit2.http.Body article: UpdateArticleRequest
    ): SingleArticleResponse
}
