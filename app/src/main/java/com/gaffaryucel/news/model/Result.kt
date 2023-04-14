package com.gaffaryucel.news.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "newstable")
data class Result(
    @ColumnInfo(name = "apiUrl")
    @SerializedName("apiUrl")
    val apiUrl: String,
    @ColumnInfo(name = "fields")
    @SerializedName("fields")
    val fields: Fields,
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @ColumnInfo(name = "isHosted")
    @SerializedName("isHosted")
    val isHosted: Boolean,
    @ColumnInfo(name = "pillarId")
    @SerializedName("pillarId")
    val pillarId: String,
    @ColumnInfo(name = "pillarName")
    @SerializedName("pillarName")
    val pillarName: String,
    @ColumnInfo(name = "sectionId")
    @SerializedName("sectionId")
    val sectionId: String,
    @ColumnInfo(name = "sectionName")
    @SerializedName("sectionName")
    val sectionName: String,
    @ColumnInfo(name = "type")
    @SerializedName("type")
    val type: String,
    @ColumnInfo(name = "webPublicationDate")
    @SerializedName("webPublicationDate")
    val webPublicationDate: String,
    @ColumnInfo(name = "webTitle")
    @SerializedName("webTitle")
    val webTitle: String,
    @ColumnInfo(name = "webUrl")
    @SerializedName("webUrl")
    val webUrl: String
)