package com.github.jmlb23.mediumclone.data

import android.util.Log
import com.github.jmlb23.mediumclone.data.apis.ArticlesApi
import com.github.jmlb23.mediumclone.data.apis.CommentsApi
import com.github.jmlb23.mediumclone.data.apis.DefaultApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Factories {
    private val retrofit = Retrofit.Builder().client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor { message ->
        Log.d(
            "CALL",
            message
        )
    }.apply { level = HttpLoggingInterceptor.Level.BODY }).build()).baseUrl("https://conduit.productionready.io/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun getDefaultService() = retrofit.create(DefaultApi::class.java)
    fun getArticlesService() = retrofit.create(ArticlesApi::class.java)
    fun getCommentsService() = retrofit.create(CommentsApi::class.java)
}