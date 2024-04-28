package com.dicoding.asclepius.data.remote.retrofit

import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.data.remote.response.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    fun getAllArticles(
        @Query("q") q: String,
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ) : Call<ArticleResponse>

}