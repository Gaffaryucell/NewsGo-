package com.gaffaryucel.news.di

import android.content.Context
import androidx.room.Room
import com.gaffaryucel.news.database.NewsDatabase
import com.gaffaryucel.news.database.UserDao
import com.gaffaryucel.news.database.UserDataBase
import com.gaffaryucel.news.service.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //gaffar yücel
    @Provides
    @Singleton
    fun ptovideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        "news_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db : NewsDatabase) = db.newsDao()

    @Provides
    @Singleton
    fun ptovideUserDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        UserDataBase::class.java,
        "userdatabase")
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideUserDao(mydb : UserDataBase) = mydb.userDao()

    private val BASE_URL = "https://content.guardianapis.com"

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

}
