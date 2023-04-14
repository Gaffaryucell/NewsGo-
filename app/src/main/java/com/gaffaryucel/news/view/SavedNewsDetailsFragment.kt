package com.gaffaryucel.news.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.gaffaryucel.news.R
import com.gaffaryucel.news.adapter.DeleteClickListener
import com.gaffaryucel.news.adapter.NewsDetailsAdapter
import com.gaffaryucel.news.adapter.SaveClickListener
import com.gaffaryucel.news.adapter.SavedNewsDetailsAdapter
import com.gaffaryucel.news.databinding.FragmentDetailsBinding
import com.gaffaryucel.news.databinding.FragmentSavedNewsDetailsBinding
import com.gaffaryucel.news.model.Result
import com.gaffaryucel.news.viewmodel.NewsViewModel

class SavedNewsDetailsFragment : Fragment() ,DeleteClickListener{
    private lateinit var binding : FragmentSavedNewsDetailsBinding
    private lateinit var viewModel : NewsViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: SavedNewsDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedNewsDetailsBinding.inflate(inflater,container,false)
        val view = binding.root
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        viewPager = binding.viewPager
        viewModel.getAllSavedNews()
        var myposition = arguments?.getInt("position")
        observeLiveData(myposition!!)
    }
    fun observeLiveData(position : Int){
        viewModel.savedNews.observe(viewLifecycleOwner, Observer {
            // Burada newsList'e veri ekleyebilirsin.
            val newsList = it as ArrayList
            adapter = SavedNewsDetailsAdapter(newsList,this)
            viewPager.adapter = adapter
            adapter.notifyDataSetChanged()
            viewPager.setCurrentItem(position, false)
        })
        viewModel.savedNews.observe(viewLifecycleOwner, Observer {
            adapter.savedList = it as ArrayList<Result>
        })
    }
    override fun onDeleteClick(news: Result) {
        viewModel.deleteNews(news)
    }
}