package com.gaffaryucel.news.view

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaffaryucel.news.R
import com.gaffaryucel.news.adapter.NewsAdapter
import com.gaffaryucel.news.database.NewsDao
import com.gaffaryucel.news.databinding.FragmentNewsBinding
import com.gaffaryucel.news.model.Fields
import com.gaffaryucel.news.model.News
import com.gaffaryucel.news.model.Result
import com.gaffaryucel.news.model.User
import com.gaffaryucel.news.viewmodel.NewsViewModel
import javax.inject.Inject


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel : NewsViewModel
    private var adapter = NewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        observeLiveData()
    }
    fun observeLiveData(){
        println("observe livedata")
        binding.newsRecyclerView.adapter = adapter
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.newsLiveData.observe(viewLifecycleOwner,Observer{
            adapter.newsList = it.response.results as ArrayList
            adapter.notifyDataSetChanged()
            binding.newsRecyclerView.visibility = View.VISIBLE
            viewModel.refresh.value = false
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.newsRecyclerView.visibility = View.INVISIBLE
                binding.progress.visibility = View.VISIBLE
                binding.errorText.visibility = View.INVISIBLE
            }else{
                binding.progress.visibility = View.INVISIBLE
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.newsRecyclerView.visibility = View.INVISIBLE
                binding.progress.visibility = View.INVISIBLE
                binding.errorText.visibility = View.VISIBLE
            }else{
                binding.errorText.visibility = View.INVISIBLE
            }
        })
        viewModel.refresh.observe(viewLifecycleOwner, Observer {
            if (it){
                viewModel.newsLiveData.observe(viewLifecycleOwner,Observer{
                    adapter.newsList = it.response.results as ArrayList
                    adapter.notifyDataSetChanged()
                    binding.newsRecyclerView.visibility = View.VISIBLE
                    viewModel.refresh.value = false
                })
            }
        })
    }
}