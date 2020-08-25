package com.startted.core_network.data

import com.startted.model.Article
import retrofit2.http.GET

interface ApiClient {
    @GET("multik/master/articles.json")
    suspend fun getArticles(): List<Article>
}