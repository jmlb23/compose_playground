package com.github.jmlb23.mediumclone.data.apis

import com.github.jmlb23.mediumclone.data.models.*
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CommentsApi {

    @Headers(
      "Content-Type: application/json"
    )
    @POST("articles/{slug}/comments")
    suspend fun createArticleComment(
        @retrofit2.http.Path("slug") slug: String,
        @retrofit2.http.Body comment: NewCommentRequest
    ): SingleCommentResponse

    @Headers(
      "Content-Type: application/json"
    )
    @DELETE("articles/{slug}/comments/{id}")
    suspend fun deleteArticleComment(
        @retrofit2.http.Path("slug") slug: String,
        @retrofit2.http.Path("id") id: Int
    ): Unit

    @Headers(
      "Content-Type: application/json"
    )
    @GET("articles/{slug}/comments")
    suspend fun getArticleComments(
        @retrofit2.http.Path("slug") slug: String
    ): MultipleCommentsResponse
}
