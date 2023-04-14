package com.gaffaryucel.news.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gaffaryucel.news.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category : User)

    @Query("SELECT * FROM user")
    fun getCategory() : User

    @Query("DELETE FROM user")
    fun clearCategory()
}