package com.gaffaryucel.news.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.gaffaryucel.news.model.User
import com.gaffaryucel.news.repo.FieldsConverter
import java.util.*
import java.util.Arrays.*

@Database(entities = [User::class], version = 1)
abstract class UserDataBase : RoomDatabase(){
    abstract fun userDao() : UserDao
}
