package com.gaffaryucel.news.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gaffaryucel.news.model.Result
import com.gaffaryucel.news.model.User
import com.gaffaryucel.news.repo.FieldsConverter


@Database(entities = [Result::class], version = 1)
@TypeConverters(FieldsConverter::class)
 abstract class NewsDatabase : RoomDatabase(){
    abstract fun newsDao() : NewsDao
}


