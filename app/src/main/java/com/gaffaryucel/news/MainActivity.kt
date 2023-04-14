package com.gaffaryucel.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.gaffaryucel.news.database.NewsDao
import com.gaffaryucel.news.databinding.ActivityMainBinding
import com.gaffaryucel.news.model.Fields
import com.gaffaryucel.news.model.News
import com.gaffaryucel.news.view.NewsNavigationActivity
import com.gaffaryucel.news.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//https://content.guardianapis.com/search?q=teknology%20since&tag=technology/technology&tag=science/science&show-fields=thumbnail,headline,trailText,bodyText,shortUrl&api-key=de7bc2c6-0665-450f-8dca-40a3b6b445c5
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}