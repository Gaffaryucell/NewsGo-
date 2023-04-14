package com.gaffaryucel.news.model


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Fields(
    @ColumnInfo(name = "bodyText")
    @SerializedName("bodyText")
    val bodyText: String,
    @ColumnInfo(name = "headline")
    @SerializedName("headline")
    val headline: String,
    @ColumnInfo(name = "shortUrl")
    @SerializedName("shortUrl")
    val shortUrl: String,
    @ColumnInfo(name = "thumbnail")
    @SerializedName("thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "trailText")
    @SerializedName("trailText")
    val trailText: String
)