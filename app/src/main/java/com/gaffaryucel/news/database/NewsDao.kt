package com.gaffaryucel.news.database

import androidx.room.*
import com.gaffaryucel.news.model.User

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNews(news: com.gaffaryucel.news.model.Result)

    @Update
    fun update(news: com.gaffaryucel.news.model.Result)

    @Delete
    fun deleteNews(news: com.gaffaryucel.news.model.Result)

    @Query("DELETE FROM newstable")
    fun deleteAllNews()

    @Query("SELECT * FROM newstable")
    fun getAllNews(): List<com.gaffaryucel.news.model.Result>

    @Query("SELECT * FROM newstable WHERE id = :id")
    fun getOneNews(id : String) : com.gaffaryucel.news.model.Result
}