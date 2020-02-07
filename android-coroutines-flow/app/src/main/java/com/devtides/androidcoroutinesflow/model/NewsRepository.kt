package com.devtides.androidcoroutinesflow.model

import com.devtides.androidcoroutinesretrofit.model.NewsArticle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository {

    private val newsService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsService::class.java)

    fun getNewsArticle(): Flow<NewsArticle> = flow {
        val newsSource = newsService.getNews()
        newsSource.forEach {
            emit(it)
            delay(NEWS_DELAY)
        }
    }

    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/DevTides/NewsApi/master/"
        private const val NEWS_DELAY = 3000L // <- 3 seconds
    }
}