package com.gaffaryucel.news.viewmodel

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaffaryucel.news.database.NewsDao
import com.gaffaryucel.news.database.UserDao
import com.gaffaryucel.news.model.News
import com.gaffaryucel.news.model.User
import com.gaffaryucel.news.service.NewsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private var repository : NewsDao,
    private val userDao : UserDao,
    private val myApi : NewsApi
) : ViewModel(){
    val refresh = MutableLiveData<Boolean>()
    var newsLiveData = MutableLiveData<News>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    var savedNews = MutableLiveData<List<com.gaffaryucel.news.model.Result>>()
    var mynewscategory = User("")
    var newsTags = MutableLiveData<List<String>>()

    init {
        newsFromApi()
    }

    fun newsFromApi(query : String? = null){
        getCategory()
        var newText = ""
        if (query.equals("")||query == null||query=="gaffaryucel"){
            newText = mynewscategory.category
        }else{
            newText = query
        }
        //https://content.guardianapis.com/search?q=technology&show-fields=thumbnail
        // ,headline,trailText,bodyText,shortUrl&api-key=
        // de7bc2c6-0665-450f-8dca-40a3b6b445c5
        loading.value = true
        println("loading = true")
        val apiKey = "de7bc2c6-0665-450f-8dca-40a3b6b445c5"
        val showFields = "thumbnail,headline,trailText,bodyText,shortUrl"
        disposable.add(
            myApi.searchNews(newText,showFields,100,apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<News>() {
                    override fun onSuccess(t: News) {
                        println("loading = succes")
                        newsLiveData.value = t
                        loading.value = false
                        refresh.value = false
                    }
                    override fun onError(e: Throwable) {
                        loading.value = false
                        error.value = true
                        println("error : "+e.localizedMessage)
                        println("loading = error")
                    }
                })
        )
    }
    fun changeCategory(mycategory : User){
        disposable.clear()
        mynewscategory = mycategory
        refresh.value = true
        userDao.insertCategory(mycategory)
        newsFromApi(mycategory.category)
    }
    fun getCategory(){
        mynewscategory = userDao.getCategory()
        if (mynewscategory == null){
            mynewscategory = User("since")
        }
    }
    fun deleteCategory(){
        userDao.clearCategory()
    }
    fun saveNews(news : com.gaffaryucel.news.model.Result){
        repository.saveNews(news)
    }
    fun getAllSavedNews(){
        savedNews.value = repository.getAllNews()
    }
    fun deleteNews(news: com.gaffaryucel.news.model.Result){
        repository.deleteNews(news)
    }

}