package com.gaffaryucel.news.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaffaryucel.news.R
import com.gaffaryucel.news.adapter.*
import com.gaffaryucel.news.database.NewsDatabase
import com.gaffaryucel.news.databinding.FragmentSavedNewsBinding
import com.gaffaryucel.news.model.Fields
import com.gaffaryucel.news.model.Result
import com.gaffaryucel.news.viewmodel.NewsViewModel

class SavedNewsFragment : Fragment() , DeleteClickListener{
    private lateinit var adapter : SavedNewsAdapter
    private lateinit var binding :FragmentSavedNewsBinding
    private lateinit var viewModel : NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedNewsBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        viewModel.getAllSavedNews()
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        observeLiveData()

    }
    fun observeLiveData(){
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.savedNews.observe(viewLifecycleOwner, Observer{
            var newsList = it as ArrayList<Result>
            adapter = SavedNewsAdapter(newsList,this)
            binding.newsRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    override fun onDeleteClick(news: Result) {
        viewModel.deleteNews(news)
    }
}