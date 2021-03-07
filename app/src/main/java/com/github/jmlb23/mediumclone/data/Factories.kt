package com.github.jmlb23.mediumclone.data

import android.util.Log
import com.github.jmlb23.mediumclone.data.apis.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object Factories {
    private val retrofit = Retrofit.Builder()
        .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor { message ->
            Log.d(
                "CALL",
                message
            )
        }.apply { level = HttpLoggingInterceptor.Level.BODY }).build())
        .baseUrl("https://conduit.productionready.io/api/")
        .addConverterFactory(Json {
            isLenient = true
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType())).build()

    fun getDefaultService() = retrofit.create(DefaultApi::class.java)
    fun getArticlesService() = retrofit.create(ArticlesApi::class.java)
    fun getCommentsService() = retrofit.create(CommentsApi::class.java)
    fun getUserAndAuthService() = retrofit.create(UserAndAuthenticationApi::class.java)
    fun getFavoritesService() = retrofit.create(FavoritesApi::class.java)
}