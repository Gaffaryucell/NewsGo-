package com.gaffaryucel.news.service

import com.gaffaryucel.news.model.News
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("search")
    fun searchNews(
        @Query("q") query: String,
        @Query("show-fields") showFields: String,
        @Query("page-size") pagesize : Int,
        @Query("api-key") apiKey: String
    ): Single<News>
}


//https://content.guardianapis.com/search?q=teknology%20since&tag=technology/technology&tag=science/science&show-fields=thumbnail,headline,trailText,bodyText,shortUrl&api-key=de7bc2c6-0665-450f-8dca-40a3b6b445c5
///search?q=teknology%20since&tag=technology/technology&tag=science/science&show-fields=thumbnail,headline,trailText,bodyText,shortUrl&api-key=de7bc2c6-0665-450f-8dca-40a3b6b445c5
//https://content.guardianapis.com/search?q=technology&show-fields=thumbnail,headline,trailText,bodyText,shortUrl&api-key=de7bc2c6-0665-450f-8dca-40a3b6b445c5
