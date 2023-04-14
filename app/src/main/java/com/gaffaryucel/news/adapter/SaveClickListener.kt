package com.gaffaryucel.news.adapter

interface SaveClickListener {
    fun onNewsItemClicked(news: com.gaffaryucel.news.model.Result)
}
interface DeleteClickListener {
    fun onDeleteClick(news: com.gaffaryucel.news.model.Result)
}
