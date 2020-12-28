package com.github.jmlb23.mediumclone.data

import com.github.jmlb23.mediumclone.data.apis.ArticlesApi
import com.github.jmlb23.mediumclone.data.apis.DefaultApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Factories {
    private val retrofit = Retrofit.Builder().baseUrl("https://conduit.productionready.io/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun getDefaultService() = retrofit.create(DefaultApi::class.java)
    fun getArticlesService() = retrofit.create(ArticlesApi::class.java)
}