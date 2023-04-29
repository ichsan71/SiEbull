package com.marqumil.siebull.data.remote.retrofit

import com.marqumil.siebull.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything?q=bullying&from=2023-02-15&sortBy=publishedAt&apiKey=613b56eec5314e60a57e3e033c454fbb")
    suspend fun getNews(@Query("apiKey") apiKey: String): NewsResponse
}