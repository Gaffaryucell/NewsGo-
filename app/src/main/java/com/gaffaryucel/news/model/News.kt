package com.gaffaryucel.news.model


import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("response")
    val response: Response
)