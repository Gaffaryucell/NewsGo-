package com.gaffaryucel.news.repo

import androidx.room.TypeConverter
import com.gaffaryucel.news.database.NewsDao
import com.gaffaryucel.news.model.Fields
import com.google.gson.Gson
import javax.inject.Inject

class FieldsConverter {
    @TypeConverter
    fun fromString(value: String): Fields {
        return Gson().fromJson(value, Fields::class.java)
    }

    @TypeConverter
    fun toString(fields: Fields): String {
        return Gson().toJson(fields)
    }
}
