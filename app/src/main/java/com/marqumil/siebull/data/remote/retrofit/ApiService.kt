package com.marqumil.siebull.data.remote.retrofit

import com.marqumil.siebull.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines?country=us&apiKey=613b56eec5314e60a57e3e033c454fbb")
    suspend fun getNews(@Query("apiKey") apiKey: String): NewsResponse
}