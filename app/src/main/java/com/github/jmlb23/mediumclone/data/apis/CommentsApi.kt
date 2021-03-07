package com.github.jmlb23.mediumclone.data.apis

import com.github.jmlb23.mediumclone.data.models.*
import retrofit2.http.*

interface CommentsApi {

    @Headers(
      "Content-Type: application/json"
    )
    @POST("articles/{slug}/comments")
    suspend fun createArticleComment(
        @Path("slug") slug: String,
        @Body comment: NewCommentRequest
    ): SingleCommentResponse

    @Headers(
      "Content-Type: application/json"
    )
    @DELETE("articles/{slug}/comments/{id}")
    suspend fun deleteArticleComment(
        @Path("slug") slug: String,
        @Path("id") id: Int
    ): Unit

    @Headers(
      "Content-Type: application/json"
    )
    @GET("articles/{slug}/comments")
    suspend fun getArticleComments(
        @Path("slug") slug: String
    ): MultipleCommentsResponse
}
