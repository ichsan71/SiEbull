package com.marqumil.siebull.data.di

import android.content.Context
import com.marqumil.siebull.data.NewsRepository
import com.marqumil.siebull.data.local.room.NewsDatabase
import com.marqumil.siebull.data.remote.retrofit.ApiConfig
import com.marqumil.siebull.utils.AppExecutors



object Injection {

    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        val appExecutors = AppExecutors()
        return NewsRepository.getInstance(apiService, dao, appExecutors)
    }

}