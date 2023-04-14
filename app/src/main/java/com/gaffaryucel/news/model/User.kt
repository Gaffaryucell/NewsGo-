package com.gaffaryucel.news.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(
    @PrimaryKey
    @ColumnInfo(name = "category")
    val category: String,
)